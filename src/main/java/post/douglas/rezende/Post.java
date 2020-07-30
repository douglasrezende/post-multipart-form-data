package post.douglas.rezende;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
/**
 * @author DouglasRezende
 *
 */
public class Post 
{
	public static void main( String[] args ) throws IOException
	{
		sendPost();
	}


	private static void sendPost() throws IOException{
		//http://10.116.88.208:5033/Douglas
		URL serverUrl =	new URL(System.getenv("endPointBankSISNP"));
		HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
		String boundaryString = "250620885631025994719935";
		String fileUrl = "C:\\\\WORK\\\\arquivo_remessa.zip";
		File logFileToUpload = new File(fileUrl);
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		//urlConnection.addRequestProperty("Authorization","Bearer + token);
		urlConnection.addRequestProperty("Accept","*/*");
		urlConnection.addRequestProperty("Accept-Encoding","gzip, deflate" );
		urlConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
		OutputStream outputStreamToRequestBody = urlConnection.getOutputStream();
		BufferedWriter httpRequestBodyWriter =
				new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));
		httpRequestBodyWriter.write("--"+boundaryString + "\r\n");
		httpRequestBodyWriter.write("Content-Disposition: form-data;"
				+ " name=\"\";"
				+ " filename=\""+ logFileToUpload.getName() +"\""+"\r\n"
				+ "Content-Type: application/octet-stream\r\n");
		httpRequestBodyWriter.write("\r\n");
		httpRequestBodyWriter.flush();
		FileInputStream inputStreamToLogFile = new FileInputStream(logFileToUpload);
		int bytesRead;
		byte[] dataBuffer = new byte[1024];
		while((bytesRead = inputStreamToLogFile.read(dataBuffer)) != -1) {
			outputStreamToRequestBody.write(dataBuffer, 0, bytesRead);
		}
		outputStreamToRequestBody.flush();
		httpRequestBodyWriter.write("\r\n--" + boundaryString + "--\r\n");
		httpRequestBodyWriter.write("\r\n");
		httpRequestBodyWriter.flush();
		outputStreamToRequestBody.close();
		httpRequestBodyWriter.close();
		System.out.println(urlConnection.getResponseCode() );
		System.out.println(urlConnection.getResponseMessage());
		StringBuffer response = new StringBuffer();
		BufferedReader in = null;
		try {
			if(urlConnection.getResponseCode() != 200) {
				in = new BufferedReader(
						new InputStreamReader(urlConnection.getErrorStream()));
			}else {
				in = new BufferedReader(
						new InputStreamReader(urlConnection.getInputStream()));
			}
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println("response:"+response.toString());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		String responseCode = Integer.toString(urlConnection.getResponseCode());
		System.out.println("ResponseCode"+responseCode);
	}
}
