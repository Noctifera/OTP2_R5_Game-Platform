/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import static org.junit.Assert.*;
import org.junit.Test;


import application.Main;


public class CreateMapTestfx extends GuiTest {
    
  
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = new Main();
            return parent;
        } catch (Exception ex) {
            System.out.println("Exception!");
        }
        return parent;
    }

    public void testStart() throws Exception {
        System.out.println("start");
        Stage stage = null;
        Main instance = new Main();
        instance.start(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void createMap() {
    	
    }

    
}
