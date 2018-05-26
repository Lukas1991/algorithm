import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class HTTPRequestTest {
    private String host = "nj-rp.ipsoft.com";

    private String token = "cwt=AAEBHAEFAAAAAAAFFQAAANz03DsuQ6xAB-U7K3yFAACBEBUTVWulRKhftCre06OKSveCAgghgyBAJ_THbZjk1M3ICsm1apTszG7HcKGll6HUDlc4i_fEEoYIU0vz-ojC1QgNEBDrIobFox9WpyA_EhuC5Mk";

    private String boundary = "77f2569d-c005-442b-b856-782305305e5f";

    @Test
    public void test() {
        List<String> parts = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            parts.add(createDataPart(boundary));
        }

        String data = parts.stream().collect(Collectors.joining("\r\n"));
        data += "\r\n\r\n--" + boundary + "--\r\n";

        try {

            HttpResponse<String> response =
                Unirest.post("https://" + host + "/ucwa/oauth/v1/applications/10820256145/batch")
                    .header("authorization", "Bearer " + token)
                    .header("content-type", "multipart/batching;boundary=" + boundary)
                    .header("accept", "multipart/batching")
                    .body(data)
                    .asString();

            System.out.println("Response1 \n" + response.getBody());

        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    String createDataPart(String boundary) {
        String dataPart = "\r\n--" + boundary;
        dataPart += "\r\nContent-Type: application/http; msgtype=request\r\n";
        dataPart += "\r\n" + "GET /ucwa/oauth/v1/applications/10820256145/people/test.user@domain.com/presence HTTP/1.1";
        dataPart += "\r\n" + "Host: " + host;
        dataPart += "\r\n" + "Accept: " + "application/json";

//        if (part.Data) {
//            dataPart += "\r\n" + "Data: " + JSON.stringify(part.Data);
//        }
        dataPart += "\r\n";
        return dataPart;
    }

}
