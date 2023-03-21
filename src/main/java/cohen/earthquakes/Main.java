package cohen.earthquakes;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main extends JFrame
{
    public static void main(String[] args) throws IOException
    {
        DisplayFrame frame = new DisplayFrame();
        frame.setVisible(true);
    }
}
