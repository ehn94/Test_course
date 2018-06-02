//package REST;
//
//import Model.Book;
//import java.io.IOException;
//import java.sql.ResultSet;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.entity.ContentType;
//import org.apache.http.impl.client.HttpClientBuilder;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//import org.junit.Test;
//
//public class IntegrationTest {
//
//    String baseURL = "http://localhost:8080/ProjectGutenberg/api/api/";
//
//    @Test
//    public void givenExistingCityReturn200() throws IOException {
//
//        String city = "London";
//        HttpUriRequest request = new HttpGet(baseURL + "getBookAuthorByCity/" + city);
//
//        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
//
//        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(HttpStatus.SC_OK)));
//    }
//
//    @Test
//    public void givenNonExistingCityReturn500() throws IOException {
//
//        String city = "Karlslunde";
//        HttpUriRequest request = new HttpGet(baseURL + "getBookAuthorByCity/" + city);
//
//        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
//        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(HttpStatus.SC_INTERNAL_SERVER_ERROR)));
//    }
//
//    @Test
//    public void checkMimeType() throws IOException {
//        String city = "London";
//        String jsonMimeType = "application/json";
//        HttpUriRequest request = new HttpGet(baseURL + "getBookAuthorByCity/" + city);
//
//        HttpResponse response = HttpClientBuilder.create().build().execute(request);
//
//        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
//        assertThat(jsonMimeType, is(equalTo(mimeType)));
//    }
//
//    
//}
