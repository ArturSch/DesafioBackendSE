package br.com.softexpert.desafio.api.rest;

import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.softexpert.desafio.domain.Friend;
import br.com.softexpert.desafio.exception.FriendNotFoundException;
import br.com.softexpert.desafio.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/desafio/v1/friends")
@Api(tags = { "friends" })
public class FriendsController {

	@Autowired
	private FriendService friendService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a Friend.", notes = "Returns the URL of the new resource in the Location header.")
	public void createFriend(@RequestBody Friend friend, HttpServletRequest request, HttpServletResponse response) {
		Friend createdFriend = this.friendService.createFriend(friend);
		response.setHeader("Location", request.getRequestURL().append("/").append(createdFriend.getFriendId()).toString());
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a paginated list of all friends.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public @ResponseBody Iterable<Friend> getAllFriend(HttpServletRequest request, HttpServletResponse response) {
		return this.friendService.getAllFriends();
	}


	
	@PutMapping(value = "/{id}", consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Update a friend resource.", notes = "You have to provide a valid friend ID in the URL and in the payload. The ID attribute can not be updated.")
	public void updateFriend(
			@ApiParam(value = "The ID of the existing friend resource.", required = true) @PathVariable("id") Long id,
			@RequestBody Friend friend, HttpServletRequest request, HttpServletResponse response)
			throws DataFormatException {
		checkResourceFound(this.friendService.getFriend(id));
		if (id != friend.getFriendId())
			throw new DataFormatException("ID doesn't match!");
		this.friendService.updateFriend(friend);
	}

	@DeleteMapping(value = "/{id}", produces = { "application/json",
			"application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a friend resource.", notes = "You have to provide a valid friend ID in the URL. Once deleted the resource can not be recovered.")
	public void deleteFriend(
			@ApiParam(value = "The ID of the existing friend resource.", required = true) @PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
		checkResourceFound(this.friendService.getFriend(id));
		this.friendService.deleteFriend(id);
	}

	public static <T> T checkResourceFound(final T resource) {
		if (resource == null) {
			throw new FriendNotFoundException("resource not found");
		}
		return resource;
	}
}
