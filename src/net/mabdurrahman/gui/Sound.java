package net.mabdurrahman.gui;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * The Sound Class contains the background, game over, racquet, and return racquet sounds.
 * @author:  MAbdurrahman
 * @date:  September 22, 2014
 */

public class Sound {
    public static final AudioClip BACKGROUND = Applet.newAudioClip(Sound.class
            .getResource("../res/audio/background.wav"));
    public static final AudioClip GAME_OVER = Applet.newAudioClip(Sound.class
            .getResource("../res/audio/gameOver.wav"));
    public static final AudioClip PADDLE_SOUND = Applet.newAudioClip(Sound.class
            .getResource("../res/audio/paddle.wav"));
    public static final AudioClip RETURN_SOUND = Applet.newAudioClip(Sound.class
            .getResource("../res/audio/returnPaddle.wav"));


}//end of the Sound Class
