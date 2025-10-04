// 代码生成时间: 2025-10-04 20:00:52
// CertificateManagementSystem.java
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

// 定义证书管理系统的主要应用程序类
@ApplicationPath("/api")
public class CertificateManagementSystem extends Application {

    // 存储证书信息的Map
    private Map<String, String> certificates = new HashMap<>();

    // RESTful API端点，用于添加或更新证书
    @Path("/certificates")
    public static class CertificateResource {

        // 添加或更新证书信息
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response addOrUpdateCertificate(String certId, String certDetails) {
            // 检查证书ID和详细信息是否有效
            if (certId == null || certDetails == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid certificate ID or details.").build();
            } else {
                // 添加或更新证书信息到Map
                CertificateManagementSystem.certificates.put(certId, certDetails);
                return Response.ok("Certificate added/updated successfully.").build();
            }
        }

        // RESTful API端点，用于获取所有证书
        @GET
        @Path("/list")
        @Produces(MediaType.APPLICATION_JSON)
        public Response listCertificates() {
            return Response.ok(CertificateManagementSystem.certificates).build();
        }

        // RESTful API端点，用于获取特定证书
        @GET
        @Path("/{certId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getCertificate(String certId) {
            String certDetails = CertificateManagementSystem.certificates.get(certId);
            if (certDetails == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Certificate not found.").build();
            } else {
                return Response.ok(certDetails).build();
            }
        }

        // RESTful API端点，用于删除特定证书
        @GET
        @Path("/delete/{certId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteCertificate(String certId) {
            if (CertificateManagementSystem.certificates.remove(certId) == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Certificate not found.").build();
            } else {
                return Response.ok("Certificate deleted successfully.").build();
            }
        }
    }

    // 运行Quarkus应用程序
    public static void main(String... args) {
        Quarkus.run(CertificateManagementSystem.class, args);
    }

    // 应用程序类结束
}

// 运行应用程序
public interface QuarkusApplication {
    void run(QuarkusApplication);
}