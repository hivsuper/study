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

import org.lxp.model.Blog;
import org.lxp.service.BlogService;
import org.springframework.stereotype.Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @see http://howtodoinjava.com/jersey/jersey-2-hello-world-application-
 *      tutorial/#service
 */
@Path("/blog")
@Api(value = "/blog")
@Service
public class BlogResource {
  @Resource
  private BlogService blogService;

  @POST
  @Path("/add")
  @ApiOperation(value = "添加博客, 学习XML映射文件")
  public Response add(@ApiParam(value = "标题") @QueryParam("title") final String title) {
    return Response.status(blogService.add(title) ? Response.Status.OK : Response.Status.BAD_REQUEST).build();
  }

  @GET
  @Path("/{id}-mapper")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "查看作者, 通过Mapper类查找")
  public Blog getViaMapper(@ApiParam(value = "博客id") @PathParam("id") final int id) {
    return blogService.getViaMapper(id);
  }

  @GET
  @Path("/{id}-sqlmapper")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "查看作者, 通过@SELECT+Mapper类查找")
  public Blog getViaSQLMapper(@ApiParam(value = "博客id") @PathParam("id") final int id) {
    return blogService.getViaSQLMapper(id);
  }

  @GET
  @Path("/{id}-sessionFactory")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "查看作者, 通过SessionFactory类查找")
  public Blog getViaSqlSessionFactory(@ApiParam(value = "博客id") @PathParam("id") final int id) {
    return blogService.getViaSqlSessionFactory(id);
  }

  @GET
  @Path("/{id}-sessionTemplate")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "查看作者, 通过SessionTemplate类查找")
  public Blog getViaSqlSessionTemplate(@ApiParam(value = "博客id") @PathParam("id") final int id) {
    return blogService.getViaSqlSessionTemplate(id);
  }
}
