package com.example.commuteeazy.network;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by folio on 9/1/2018.
 */

public class TLS12SocketFactory extends SSLSocketFactory {
    private final List<String> disabledProtocols;

    private final SSLSocketFactory delegate;

    public TLS12SocketFactory() {
        this(null);
    }

    public TLS12SocketFactory(SSLSocketFactory delegate) {
        if (delegate == null) {
            try {
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, null, null);
                delegate = context.getSocketFactory();
            } catch (Exception ex) {
                delegate = HttpsURLConnection.getDefaultSSLSocketFactory();
            }
        }

        disabledProtocols = new ArrayList<>(3);
        disabledProtocols.add("SSLv3");
        disabledProtocols.add("TLSv1");
        disabledProtocols.add("TLSv1.1");

        this.delegate = delegate;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

    private Socket strippedProtocolSocket(Socket socket) {
        if (socket instanceof SSLSocket) {
            socket = new Tls12OnlySocket((SSLSocket) socket);
        }
        return socket;
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return strippedProtocolSocket(delegate.createSocket(s, host, port, autoClose));
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return strippedProtocolSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        return strippedProtocolSocket(delegate.createSocket(host, port, localHost, localPort));
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return strippedProtocolSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(address, port, localAddress, localPort));
    }

    private Socket enableTLSOnSocket(Socket socket) {
        if(socket != null && (socket instanceof SSLSocket)) {
            //Create list of supported protocols
            ArrayList<String> supportedProtocols = new ArrayList<>();
            for (String protocol : ((SSLSocket)socket).getEnabledProtocols()) {

                //Log.d("TLSSocketFactory", "Supported protocol:" + protocol);
                //Only add TLS protocols (don't want ot support older SSL versions)
                if (protocol.toUpperCase().contains("TLS")) {
                    supportedProtocols.add(protocol);
                }
            }
            //Force add TLSv1.1 and 1.2 if not already added
            if (!supportedProtocols.contains("TLSv1.1")) {
                supportedProtocols.add("TLSv1.1");
            }
            if (!supportedProtocols.contains("TLSv1.2")) {
                supportedProtocols.add("TLSv1.2");
            }

            String[] protocolArray = supportedProtocols.toArray(new String[supportedProtocols.size()]);
        /*for (int i = 0; i < protocolArray.length; i++) {
            Log.d("TLSSocketFactory", "protocolArray[" + i + "]" + protocolArray[i]);
        }*/

            //enable protocols in our list
            ((SSLSocket)socket).setEnabledProtocols(protocolArray);
        }
        return socket;
    }

    private class Tls12OnlySocket extends DelegateSSLSocket {

        private Tls12OnlySocket(SSLSocket delegate) {
            super(delegate);
            delegate.setEnabledProtocols(new String[]{"TLSv1.2"});
        }

        @Override
        public void setEnabledProtocols(String[] protocols) {
        }

        @Override
        public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {

        }

        @Override
        public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {

        }
    }

    private class DelegateSSLSocket extends SSLSocket {

        protected final SSLSocket delegate;

        DelegateSSLSocket(SSLSocket delegate) {
            this.delegate = delegate;
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return delegate.getSupportedCipherSuites();
        }

        @Override
        public String[] getEnabledCipherSuites() {
            return delegate.getEnabledCipherSuites();
        }

        @Override
        public void setEnabledCipherSuites(String[] suites) {
            delegate.setEnabledCipherSuites(suites);
        }

        @Override
        public String[] getSupportedProtocols() {
            return delegate.getSupportedProtocols();
        }

        @Override
        public String[] getEnabledProtocols() {
            return delegate.getEnabledProtocols();
        }

        @Override
        public void setEnabledProtocols(String[] protocols) {
            delegate.setEnabledProtocols(protocols);
        }

        @Override
        public SSLSession getSession() {
            return delegate.getSession();
        }

        @Override
        public void addHandshakeCompletedListener(HandshakeCompletedListener listener) {
            delegate.addHandshakeCompletedListener(listener);
        }

        @Override
        public void removeHandshakeCompletedListener(HandshakeCompletedListener listener) {
            delegate.removeHandshakeCompletedListener(listener);
        }

        @Override
        public void startHandshake() throws IOException {
            delegate.startHandshake();
        }

        @Override
        public void setUseClientMode(boolean mode) {
            delegate.setUseClientMode(mode);
        }

        @Override
        public boolean getUseClientMode() {
            return delegate.getUseClientMode();
        }

        @Override
        public void setNeedClientAuth(boolean need) {
            delegate.setNeedClientAuth(need);
        }

        @Override
        public void setWantClientAuth(boolean want) {
            delegate.setWantClientAuth(want);
        }

        @Override
        public boolean getNeedClientAuth() {
            return delegate.getNeedClientAuth();
        }

        @Override
        public boolean getWantClientAuth() {
            return delegate.getWantClientAuth();
        }

        @Override
        public void setEnableSessionCreation(boolean flag) {
            delegate.setEnableSessionCreation(flag);
        }

        @Override
        public boolean getEnableSessionCreation() {
            return delegate.getEnableSessionCreation();
        }

        @Override
        public void bind(SocketAddress localAddr) throws IOException {
            delegate.bind(localAddr);
        }

        @Override
        public synchronized void close() throws IOException {
            delegate.close();
        }

        @Override
        public void connect(SocketAddress remoteAddr) throws IOException {
            delegate.connect(remoteAddr);
        }

        @Override
        public void connect(SocketAddress remoteAddr, int timeout) throws IOException {
            delegate.connect(remoteAddr, timeout);
        }

        @Override
        public SocketChannel getChannel() {
            return delegate.getChannel();
        }

        @Override
        public InetAddress getInetAddress() {
            return delegate.getInetAddress();
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return delegate.getInputStream();
        }

        @Override
        public boolean getKeepAlive() throws SocketException {
            return delegate.getKeepAlive();
        }

        @Override
        public InetAddress getLocalAddress() {
            return delegate.getLocalAddress();
        }

        @Override
        public int getLocalPort() {
            return delegate.getLocalPort();
        }

        @Override
        public SocketAddress getLocalSocketAddress() {
            return delegate.getLocalSocketAddress();
        }

        @Override
        public boolean getOOBInline() throws SocketException {
            return delegate.getOOBInline();
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return delegate.getOutputStream();
        }

        @Override
        public int getPort() {
            return delegate.getPort();
        }

        @Override
        public synchronized int getReceiveBufferSize() throws SocketException {
            return delegate.getReceiveBufferSize();
        }

        @Override
        public SocketAddress getRemoteSocketAddress() {
            return delegate.getRemoteSocketAddress();
        }

        @Override
        public boolean getReuseAddress() throws SocketException {
            return delegate.getReuseAddress();
        }

        @Override
        public synchronized int getSendBufferSize() throws SocketException {
            return delegate.getSendBufferSize();
        }

        @Override
        public int getSoLinger() throws SocketException {
            return delegate.getSoLinger();
        }

        @Override
        public synchronized int getSoTimeout() throws SocketException {
            return delegate.getSoTimeout();
        }

        @Override
        public boolean getTcpNoDelay() throws SocketException {
            return delegate.getTcpNoDelay();
        }

        @Override
        public int getTrafficClass() throws SocketException {
            return delegate.getTrafficClass();
        }

        @Override
        public boolean isBound() {
            return delegate.isBound();
        }

        @Override
        public boolean isClosed() {
            return delegate.isClosed();
        }

        @Override
        public boolean isConnected() {
            return delegate.isConnected();
        }

        @Override
        public boolean isInputShutdown() {
            return delegate.isInputShutdown();
        }

        @Override
        public boolean isOutputShutdown() {
            return delegate.isOutputShutdown();
        }

        @Override
        public void sendUrgentData(int value) throws IOException {
            delegate.sendUrgentData(value);
        }

        @Override
        public void setKeepAlive(boolean keepAlive) throws SocketException {
            delegate.setKeepAlive(keepAlive);
        }

        @Override
        public void setOOBInline(boolean oobinline) throws SocketException {
            delegate.setOOBInline(oobinline);
        }

        @Override
        public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
            delegate.setPerformancePreferences(connectionTime, latency, bandwidth);
        }

        @Override
        public synchronized void setReceiveBufferSize(int size) throws SocketException {
            delegate.setReceiveBufferSize(size);
        }

        @Override
        public void setReuseAddress(boolean reuse) throws SocketException {
            delegate.setReuseAddress(reuse);
        }

        @Override
        public synchronized void setSendBufferSize(int size) throws SocketException {
            delegate.setSendBufferSize(size);
        }

        @Override
        public void setSoLinger(boolean on, int timeout) throws SocketException {
            delegate.setSoLinger(on, timeout);
        }

        @Override
        public synchronized void setSoTimeout(int timeout) throws SocketException {
            delegate.setSoTimeout(timeout);
        }

        @Override
        public void setTcpNoDelay(boolean on) throws SocketException {
            delegate.setTcpNoDelay(on);
        }

        @Override
        public void setTrafficClass(int value) throws SocketException {
            delegate.setTrafficClass(value);
        }

        @Override
        public void shutdownInput() throws IOException {
            delegate.shutdownInput();
        }

        @Override
        public void shutdownOutput() throws IOException {
            delegate.shutdownOutput();
        }

        @Override
        public String toString() {
            return delegate.toString();
        }

        @Override
        public boolean equals(Object o) {
            return delegate.equals(o);
        }
    }
}
