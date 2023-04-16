package com.example.personaplayfront.Controller.Frontend;


import com.example.personaplayfront.Controller.Handler.ImageHandler;
import com.example.personaplayfront.Model.Icon;
import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Model.Roles;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Repo.IconDaoImpl;
import com.example.personaplayfront.Repo.MediaDaoImpl;
import com.example.personaplayfront.Repo.RolesDaoImpl;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PpAdminUserObjController {
    public HBox container;
    public Circle userIcon;
    
    public Text username;
    public Text email;
    public Text password;
    public CheckBox matureToggle;
    public CheckBox roleToggle;

    UsersDaoImpl userDao = new UsersDaoImpl();
    RolesDaoImpl roleDao = new RolesDaoImpl();
    IconDaoImpl iconDao = new IconDaoImpl();

    Users user;

    Icon icon;

    public void initialize() {

        //make sure the text doesnt overflow
        username.setWrappingWidth(180);
        email.setWrappingWidth(180);
        password.setWrappingWidth(180);

    }

    //set media data
    public void setUser(Users user) {
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        matureToggle.setSelected(user.isContentFilter());
        roleToggle.setSelected(user.getRole().equals("admin"));

        this.user = userDao.findByPropertyLike("username", username.getText());

        icon = iconDao.findByPropertyLike("id", user.getId());

        //fill user icon with image from ImageHandler
        ImageHandler.getImageAsync(icon.getIcon(), icon.getVariant(), image -> {
            userIcon.setFill(new ImagePattern(image));
        });

        //set role mnemonic parsing
        if (this.user.getRole().getName().equals("admin")) {
            //set toggle as active
            roleToggle.setSelected(true);
        } else {
            roleToggle.setSelected(false);
        }

        //on visible toggle, update media
        matureToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            user.setContentFilter(newValue);
            userDao.update(user);
        });

        //on available toggle on, update to admin
        roleToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Roles role = roleDao.findByPropertyLike("name", "admin");
                user.setRole(role);
            } else {
                Roles role = roleDao.findByPropertyLike("name", "user");
                user.setRole(role);
            }
            System.out.println(user);
            userDao.update(user);
        });
    }
}
