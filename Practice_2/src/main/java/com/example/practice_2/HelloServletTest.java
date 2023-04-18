package com.example.practice_2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringWriter;

public class HelloServletTest {
    @Test
    public void testDoGetWithoutNameParameter() throws IOException {
        // Arrange
        HelloServlet helloServlet = new HelloServlet();
        HttpServletRequest request = null;
        HttpServletResponse response = new HttpServletResponseStub();

        // Act
        helloServlet.doGet(request, response);
        StringWriter stringWriter = ((HttpServletResponseStub) response).getStringWriter();
        String responseText = stringWriter.toString();

        // Assert
        Assert.assertTrue(responseText.contains("<h1>Hello World!</h1>"));
    }
}
