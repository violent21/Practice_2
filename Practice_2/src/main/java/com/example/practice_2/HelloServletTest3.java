package com.example.practice_2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import static org.testng.AssertJUnit.assertTrue;

public class HelloServletTest3 {

    @Test
    public void testDoGetWithNameParameter() throws IOException {
        // Arrange
        HelloServlet helloServlet = new HelloServlet();
        HttpServletRequest request = new HttpServletRequestStub("GET", "/hello-servlet?name=John");
        HttpServletResponse response = new HttpServletResponseStub();

        // Act
        helloServlet.doGet(request, response);
        StringWriter stringWriter = ((HttpServletResponseStub) response).getStringWriter();
        String responseText = stringWriter.toString();

        // Assert
        assertTrue(responseText.contains("<h1>Hello, John!</h1>"));
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        // Arrange
        HelloServlet helloServlet = new HelloServlet();
        HttpServletRequest request = new HttpServletRequestStub("POST", "/hello-servlet");
        HttpServletResponse response = new HttpServletResponseStub();
        String fileName = "testFile.txt";
        String fileContent = "Hello, World!";
        InputStream fileInputStream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        HttpServletRequestStub requestStub = (HttpServletRequestStub) request;
        requestStub.addPart(new PartStub("file", fileName, fileInputStream));

        // Act
        helloServlet.doPost(request, response);
        StringWriter stringWriter = ((HttpServletResponseStub) response).getStringWriter();
        String responseText = stringWriter.toString();

        // Assert
        assertTrue(responseText.contains("<p>" + fileName + "</p>"));
        File uploadedFile = new File("/path/to/uploaded/files", fileName);
        assertTrue(uploadedFile.exists());
        byte[] buffer = new byte[1024];
        int bytesRead = new FileInputStream(uploadedFile).read(buffer);
        String uploadedFileContent = new String(buffer, 0, bytesRead);
        assertTrue(uploadedFileContent.equals(fileContent));
        uploadedFile.delete();
    }

    private static class HttpServletRequestStub implements HttpServletRequest {
        private final String method;
        private final String requestURI;
        private final MultiPartStub multiPart = new MultiPartStub();

        public HttpServletRequestStub(String method, String requestURI) {
            this.method = method;
            this.requestURI = requestURI;
        }

        @Override
        public String getAuthType() {
            return null;
        }

        @Override
        public Cookie[] getCookies() {
            return new Cookie[0];
        }

        @Override
        public long getDateHeader(String s) {
            return 0;
        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }

        @Override
        public int getIntHeader(String s) {
            return 0;
        }

        @Override
        public String getMethod() {
            return method;
        }

        @Override
        public String getPathInfo() {
            return null;
        }

        @Override
        public String getPathTranslated() {
            return null;
        }

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public String getQueryString() {
            return null;
        }

        @Override
        public String getRemoteUser() {
            return null;
        }

        @Override
        public boolean isUserInRole(String s) {
            return false;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public String getRequestedSessionId() {
            return null;
        }

        @Override
        public String getRequestURI() {
            return requestURI;
        }

        @Override
        public StringBuffer getRequestURL() {
            return null;
        }

        @Override
        public String getServletPath() {
            return null;
        }

        @Override
        public HttpSession getSession(boolean b) {
            return null;
        }

        @Override
        public HttpSession getSession() {
            return null;
        }

        @Override
        public String changeSessionId() {
            return null;
        }

        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromUrl() {
            return false;
        }

        @Override
        public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
            return false;
        }

        @Override
        public void login(String s, String s1) throws ServletException {

        }

        @Override
        public void logout() throws ServletException {

        }

        @Override
        public Collection<Part> getParts() throws IOException, ServletException {
            return null;
        }

        @Override
        public Part getPart(String name) throws IOException {
            return multiPart.getPart(name);
        }

        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
            return null;
        }

        public void addPart(Part part) {
            multiPart.addPart((PartStub) part);
        }

        @Override
        public Object getAttribute(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

        }

        @Override
        public int getContentLength() {
            return 0;
        }

        @Override
        public long getContentLengthLong() {
            return 0;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public String getParameter(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return null;
        }

        @Override
        public String[] getParameterValues(String s) {
            return new String[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public String getScheme() {
            return null;
        }

        @Override
        public String getServerName() {
            return null;
        }

        @Override
        public int getServerPort() {
            return 0;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }

        @Override
        public String getRemoteAddr() {
            return null;
        }

        @Override
        public String getRemoteHost() {
            return null;
        }

        @Override
        public void setAttribute(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String s) {
            return null;
        }

        @Override
        public String getRealPath(String s) {
            return null;
        }

        @Override
        public int getRemotePort() {
            return 0;
        }

        @Override
        public String getLocalName() {
            return null;
        }

        @Override
        public String getLocalAddr() {
            return null;
        }

        @Override
        public int getLocalPort() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public AsyncContext startAsync() throws IllegalStateException {
            return null;
        }

        @Override
        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
            return null;
        }

        @Override
        public boolean isAsyncStarted() {
            return false;
        }

        @Override
        public boolean isAsyncSupported() {
            return false;
        }

        @Override
        public AsyncContext getAsyncContext() {
            return null;
        }

        @Override
        public DispatcherType getDispatcherType() {
            return null;
        }
    }

    private static class MultiPartStub implements Part {
        private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        private final String boundary = "Boundary-1234567890";
        private final String contentType = "multipart/form-data; boundary=" + boundary;
        private final String crlf = "\r\n";
        private final String doubleDash = "--";
        private final String nameAttrFormat = "Content-Disposition: form-data; name=\"%s\"" + crlf + crlf;
        private final String fileAttrFormat = "Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"" + crlf + crlf;

        private PartStub[] parts;

        public MultiPartStub() {
            this.parts = new PartStub[0];
        }

        public void addPart(PartStub part) {
            PartStub[] newParts = new PartStub[parts.length + 1];
            System.arraycopy(parts, 0, newParts, 0, parts.length);
            newParts[parts.length] = part;
            this.parts = newParts;
        }

        public Part getPart(String name) throws IOException {
            for (PartStub part : parts) {
                if (part.getName().equals(name)) {
                    return part;
                }
            }
            return null;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            for (PartStub part : parts) {
                outputStream.write(doubleDash.getBytes(StandardCharsets.UTF_8));
                outputStream.write(boundary.getBytes(StandardCharsets.UTF_8));
                outputStream.write(crlf.getBytes(StandardCharsets.UTF_8));
                outputStream.write(String.format(nameAttrFormat, part.getName()).getBytes(StandardCharsets.UTF_8));
                outputStream.write(part.getContent().getBytes(StandardCharsets.UTF_8));
                outputStream.write(crlf.getBytes(StandardCharsets.UTF_8));
            }
            outputStream.write(doubleDash.getBytes(StandardCharsets.UTF_8));
            outputStream.write(boundary.getBytes(StandardCharsets.UTF_8));
            outputStream.write(doubleDash.getBytes(StandardCharsets.UTF_8));
            outputStream.write(crlf.getBytes(StandardCharsets.UTF_8));
            return new ByteArrayInputStream(outputStream.toByteArray());
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getSubmittedFileName() {
            return null;
        }

        @Override
        public long getSize() {
            return 0;
        }

        @Override
        public void write(String s) throws IOException {

        }

        @Override
        public void delete() throws IOException {

        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Collection<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Collection<String> getHeaderNames() {
            return null;
        }
    }

    private static class PartStub implements Part {
        private final String name;
        private final String fileName;
        private final InputStream inputStream;

        public PartStub(String name, String fileName, InputStream inputStream) {
            this.name = name;
            this.fileName = fileName;
            this.inputStream = inputStream;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public String getContentType() {
            return null;
        }

        public String getName() {
            return name;
        }

        @Override
        public String getSubmittedFileName() {
            return null;
        }

        @Override
        public long getSize() {
            return 0;
        }

        @Override
        public void write(String s) throws IOException {

        }

        @Override
        public void delete() throws IOException {

        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Collection<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Collection<String> getHeaderNames() {
            return null;
        }

        public String getFileName() {
            return fileName;
        }

        public String getContent() throws IOException {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    private static class HttpServletResponseStub implements HttpServletResponse {
        private StringWriter stringWriter = new StringWriter();

        public StringWriter getStringWriter() {
            return stringWriter;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return null;
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(stringWriter);
        }

        @Override
        public void setCharacterEncoding(String s) {

        }

        @Override
        public void setContentLength(int i) {

        }

        @Override
        public void setContentLengthLong(long l) {

        }

        @Override
        public void setContentType(String s) {

        }

        @Override
        public void setBufferSize(int i) {

        }

        @Override
        public int getBufferSize() {
            return 0;
        }

        @Override
        public void flushBuffer() throws IOException {

        }

        @Override
        public void resetBuffer() {

        }

        @Override
        public boolean isCommitted() {
            return false;
        }

        @Override
        public void reset() {

        }

        @Override
        public void setLocale(Locale locale) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public void addCookie(Cookie cookie) {

        }

        @Override
        public boolean containsHeader(String s) {
            return false;
        }

        @Override
        public String encodeURL(String s) {
            return null;
        }

        @Override
        public String encodeRedirectURL(String s) {
            return null;
        }

        @Override
        public String encodeUrl(String s) {
            return null;
        }

        @Override
        public String encodeRedirectUrl(String s) {
            return null;
        }

        @Override
        public void sendError(int i, String s) throws IOException {

        }

        @Override
        public void sendError(int i) throws IOException {

        }

        @Override
        public void sendRedirect(String s) throws IOException {

        }

        @Override
        public void setDateHeader(String s, long l) {

        }

        @Override
        public void addDateHeader(String s, long l) {

        }

        @Override
        public void setHeader(String s, String s1) {

        }

        @Override
        public void addHeader(String s, String s1) {

        }

        @Override
        public void setIntHeader(String s, int i) {

        }

        @Override
        public void addIntHeader(String s, int i) {

        }

        @Override
        public void setStatus(int i) {

        }

        @Override
        public void setStatus(int i, String s) {

        }

        @Override
        public int getStatus() {
            return 0;
        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Collection<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Collection<String> getHeaderNames() {
            return null;
        }
    }
}
