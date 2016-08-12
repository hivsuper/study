package org.lxp.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.lxp.model.Author;
import org.lxp.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @see http://howtodoinjava.com/jersey/jersey-2-hello-world-application-
 *      tutorial/#service
 */
@Path("/author")
@Api(value = "/author")
@Service
public class AuthorResource {
  private static final Logger LOG = LoggerFactory.getLogger(AuthorResource.class);
  @Resource
  private AuthorService authorService;

  @POST
  @Path("/add")
  @ApiOperation(value = "添加作者, 学习XML映射文件")
  public Response add(@ApiParam(value = "用户名") @QueryParam("username") final String username,
      @ApiParam(value = "密码") @QueryParam("password") final String password) {
    LOG.info("{} {}", username, password);
    return Response.status(authorService.add(username, password) ? Response.Status.OK : Response.Status.BAD_REQUEST)
        .build();
  }

  @GET
  @Path("/{username}")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "查看作者, 学习动态sql语句")
  public Author get(@ApiParam(value = "用户名") @PathParam("username") final String username) {
    LOG.info("{}", username);
    return authorService.getAuthor(username);
  }
}
