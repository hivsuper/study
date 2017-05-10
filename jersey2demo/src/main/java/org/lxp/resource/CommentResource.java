package org.lxp.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.lxp.model.Comment;
import org.lxp.service.CommentService;
import org.lxp.vo.CommentStateEnum;
import org.springframework.stereotype.Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/comment")
@Api(value = "/comment")
@Service
public class CommentResource {
  @Resource
  private CommentService commentService;

  @POST
  @Path("/add")
  @ApiOperation(value = "添加评论，学习typeHandlers")
  public Response add(@ApiParam(value = "评论的文章id") @QueryParam("postId") final int postId,
      @ApiParam(value = "评论者名称") @QueryParam("name") final String name,
      @ApiParam(value = "内容") @QueryParam("text") final String text) {
    return Response.status(commentService.add(postId, name, text) ? Response.Status.OK : Response.Status.BAD_REQUEST)
        .build();
  }

  @POST
  @Path("/update")
  @ApiOperation(value = "更新评论，学习typeHandlers")
  public Response update(@ApiParam(value = "评论id") @QueryParam("commentId") final int commentId,
      @ApiParam(value = "状态") @QueryParam("state") final CommentStateEnum state) {
    return Response.status(commentService.update(commentId, state) ? Response.Status.OK : Response.Status.BAD_REQUEST)
        .build();
  }

  @GET
  @Path("/{commentId}")
  @ApiOperation(value = "查看评论")
  public Comment get(@ApiParam(value = "评论id") @QueryParam("commentId") final int commentId) {
    return commentService.get(commentId);
  }
}
