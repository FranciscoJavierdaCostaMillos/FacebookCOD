/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionfacebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Media;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.conf.ConfigurationBuilder;
import java.awt.Window;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout.Group;
import javax.swing.JOptionPane;

/**
 *
 * @author Javi
 */
public class AplicacionFacebook {

    static Facebook facebook;

    public static void connectr() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthAppId("*****************+*")
                .setOAuthAppSecret("*******************")
                .setOAuthAccessToken("**************")
                .setOAuthPermissions("email,publish_stream,...");
        FacebookFactory ff = new FacebookFactory(cb.build());
        facebook = ff.getInstance();
    }

    public static void postearFoto() throws FacebookException, MalformedURLException {

        PostUpdate post = new PostUpdate(new URL("https://static.pexels.com/photos/7613/pexels-photo.jpg"))
                .picture(new URL("https://static.pexels.com/photos/7613/pexels-photo.jpg"))
                .name("Skyline");
        facebook.postFeed(post);

        JOptionPane.showMessageDialog(null, "Foto publicada");
    }

    public static void publicarEstado(String Comnt) throws FacebookException {
        facebook.postStatusMessage(Comnt);
        JOptionPane.showMessageDialog(null, "Estado publicado");
    }


    public static void buscarTL(String Kword) throws FacebookException {

        ResponseList<facebook4j.Group> results = facebook.searchGroups(Kword);
        for (int i = 0; i < results.size(); i++) {
            JOptionPane.showMessageDialog(null,results.get(i).getName());
        }
        
    }

    public static void comentarPost() throws FacebookException {
        String ID = JOptionPane.showInputDialog("Introducir id a comentar");
        String comentario = JOptionPane.showInputDialog("comentario:");
        facebook.commentPhoto(ID, comentario);

    }


    public static void ObtenerComentar() throws FacebookException {

        ResponseList<Post> feed = facebook.getHome();
        for (int i = 0; i < feed.size(); i++) {
            JOptionPane.showMessageDialog(null,feed.get(i));
        }
    }


    public static void main(String[] args) throws FacebookException {
        Menu apf = new Menu();
        apf.setVisible(true);
        connectr();

    }

}
