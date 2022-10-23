package me.tsaheylu.controller;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.DtoMapper.FavURLDtoMapper;
import me.tsaheylu.common.FavURLStatus;
import me.tsaheylu.common.response.ResponseResult;
import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.FavurlRepo;
import me.tsaheylu.repository.FriendRepo;
import me.tsaheylu.service.FavURLService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/favurls") // This means URL's start with /demo (after Application path)
@ResponseResult
@RequiredArgsConstructor
public class FavURLController {

    private final FavURLService favurlService;

//  /api/favurl/channel
//  /api/favurl/my
//  /api/favurl/newcoming
//  /api/favurl/pending
//  /api/favurl/search
//  /api/favurl/send
//  /api/favurl/status
//  /api/favurl/tryrecieve
//  /api/favurl/trysend

    @GetMapping(path = "/archive")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public @ResponseBody HashMap<String, Object> getArchive(@RequestParam String startCursor) {
        // This returns a JSON or XML with the users
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        return favurlService.getFavurlsByStatus(user.getId(), startCursor, FavURLStatus.ARCHIVE.getId());
    }

    @GetMapping(path = "/new")
//  @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    public @ResponseBody HashMap<String, Object> getNew(@RequestParam String startCursor) {
        // This returns a JSON or XML with the users
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        return favurlService.getFavurlsByStatus(user.getId(), startCursor, FavURLStatus.NEW.getId());
    }

    @GetMapping(path = "/fav")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public @ResponseBody HashMap<String, Object> getFav(@RequestParam String startCursor) {
        // This returns a JSON or XML with the users
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        return favurlService.getFav(user.getId(), startCursor);
    }

    @PostMapping(path = "/status")
    public @ResponseBody boolean updateStatus(@RequestBody List<FavURL> favurls) {
        // This returns a JSON or XML with the users

        return favurlService.batchUpdateFavurlStatus(favurls);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    private FavURLDTO create(@RequestBody FavURLDTO favURLDTO) {

        return favurlService.createFavurl(favURLDTO);
    }

    @PutMapping
    private FavURLDTO update(@RequestBody FavURLDTO favURLDTO) {
        return favurlService.update(favURLDTO);
    }

    @GetMapping(value = "/{id}")
    private FavURLDTO get(@PathVariable Long id) {
        return favurlService.getDto(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        favurlService.delete(id);
    }


    @PostMapping(value = "/send")
    @ResponseStatus(code = HttpStatus.CREATED)
    private List<FavURL> send(@RequestParam(required = true) Long fromId, @RequestParam(required = true) String groupIds, @RequestParam(required = true) boolean toAll, @RequestParam(required = true) boolean toMe, @RequestParam(required = true) String url, @RequestParam(required = true) String urlTitle, @RequestParam(required = true) String iconUrl) {
        return favurlService.sendFavurls(fromId, groupIds, toAll, toMe, url, urlTitle, iconUrl);
    }

    //
    //  @GetMapping(path = "/channel")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/fav")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/fav/update")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/fstatus")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/my")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/new")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/newcoming")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/pending")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/search")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/send")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/status")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/tryrecieve")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
    //
    //  @GetMapping(path = "/trysend")
    //  public @ResponseBody FavURL getArchive() {
    //    // This returns a JSON or XML with the users
    //
    //    return favurlService.getArchive(Long.valueOf(1));
    //  }
}
