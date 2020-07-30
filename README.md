# post-multipart-form-data

 Each HttpURLConnection instance is used to make a single request but the underlying network connection to the HTTP server may be transparently shared by other instances. Calling the close() methods on the InputStream or OutputStream of an HttpURLConnection after a request may free network resources associated with this instance but has no effect on any shared persistent connection. Calling the disconnect() method may close the underlying socket if a persistent connection is otherwise idle at that time.

The HTTP protocol handler has a few settings that can be accessed through System Properties. This covers Proxy settings as well as various other settings. 

Java.net.HttpURLConnection Class in Java

HttpURLConnection class is an abstract class directly extending from URLConnection class. It includes all the functionality of its parent class with additional HTTP specific features. HttpsURLConnection is another class which is used for the more secured HTTPS protocol.
Where is it used?
It is one of the popular choices among the Java developers for interacting with web servers and android developing team has officially suggested to use it wherever possible.
At the end, this article illustrates a simple implementation of an interactive application which uses microsoft emotion api to retrieve the emotion scores from an image using methods of HttpURLConnection class.

Constructor:

Syntax : protected HttpURLConnection(URL u):
Parameters : 
u - the url
Constructs the httpurlconnection to specified URL.

Methods (other than in URLConnection class):

    getResponseCode() : Used to retrieve the response status from server.

    Syntax : protected int getResponseCode()
    1xx : Information
    2xx : Success
    3xx : Redirection
    4xx : Client error
    5xx : Server error

    setRequestMethod() : Used to set the request method. Default is GET.

    Syntax :  protected void setRequestMethod(String method) 
                                    throws ProtocolException
    Parameters: 
    method : Must be any one of the following-
    GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE
    Throws :
    ProtocolException - If the method is not valid for http.

    getRequestMethod() : Returns the request method.

    Syntax :  protected String getRequestMethod()           

    getResponseMessage() :Retrieves the response message.

    Syntax :  public String getResponseMessage()           
    200 - OK
    404 - NOT FOUND

    getHeaderField() : Returns the nth header field, or null if it does not exist. It overrides getHeaderField method of URLConnection class.

    Syntax :  public String getHeaderField(int n)     
    Parameters: 
    n : Index of the header field.

    setFixedLengthStreamingMode() : Used to set the length of content written on outputstream if it is known in advance.

    Syntax :  public void setFixedLengthStreamingMode(long n/int n) 
                                           throws IllegalStateException          

    Parameters: 
    n : Length of content to be written.
    Throws:
    IllegalStateException : If specified length of content is not written on 
    outputstream.

    setFollowRedirects() : Sets whether a 3xx response code requests be redirected automatically or not.

    Syntax :  public void setFollowRedirects(boolean b)           
    Parameters: 
    b : Must be true or false.

    getFollowRedirects() : Returns true or false depending on automatic redirection or not.

    Syntax :  public static boolean getFollowRedirects()           

    disconnect() : Indicated that requests to server are highly unlikely in future.

    Syntax :  public void disconnect()           

    usingProxy() : Returns true if connection established using a proxy, else false.

    Syntax :  public boolean usingProxy()           

    setChunkedStreamingMode() : This mode is used when the content length is not known. Instead of creating a buffer of fixed length and writing it to server, content is broken into chunks and then written. Not all servers support this mode.

    Syntax :  public void setChunkedStreamingMode(int n) 
                                           throws IllegalStateException          
    Parameters: 
    n : length written in each chunk.
    Throws:
    IllegalStateException : If some different streaming mode is enabled.

    getPermission() : Retrieves the permission required to connect to destination host and port.

    Syntax :  public Permission getPermission()           

    getErrorStream() : Gets the error stream if the server cannot be connected or some error occured. It can contain information about how to fix the error from server.

    Syntax :  public InputStream getErrorStream()           

    setInstanceFollowRedirects() : Sets whether a 3xx response code requests be redirected automatically by this instance of httpURLconnection. It overrides the more generic setFollowRedirects
    parameter. If not specified, then the instance redirects based on setFollowRedirects().

    Syntax :  public void setFollowRedirects(boolean b)           
    Parameters: 
    b : Must be true or false.

    getInstanceFollowRedirects() : Returns true or false depending on automatic instance redirection set or not.

    Syntax :  public boolean getFollowRedirects()           



When you make a POST request, you have to encode the data that forms the body of the request in some way.

HTML forms provide three methods of encoding.

    application/x-www-form-urlencoded (the default)
    multipart/form-data
    text/plain
The specifics of the formats don't matter to most developers. The important points are:

    Never use text/plain.

When you are writing client-side code:

    use multipart/form-data when your form includes any <input type="file"> elements
    otherwise you can use multipart/form-data or application/x-www-form-urlencoded but application/x-www-form-urlencoded will be more efficient
