// 代码生成时间: 2025-09-12 08:12:53
package org.acme.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class RestfulApiQuarkus {

    /**
     * 提供一个 GET 请求的 API 接口
     *
     * @return 返回 JSON 格式的欢迎信息
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWelcomeMessage() {
        try {
            // 构建响应对象
            String jsonResponse = "{"message": "Welcome to the RESTful API provided by Quarkus"}";
            return Response.status(Response.Status.OK).entity(jsonResponse).build();
        } catch (Exception e) {
            // 错误处理，返回 500 状态码和错误信息
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{"error": "Internal Server Error"}").build();
        }
    }

    // 可以根据需要添加更多的 API 方法
}