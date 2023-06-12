package com.bdg.pc_build.user;

import com.bdg.pc_build.wishlist.model.request.WishlistRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/User")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserService userService;


    @PostMapping(value = "/save")
    public boolean save(
            @RequestBody UserDTO userDTO
    ){
        return userService.save(userDTO);
    }


}
