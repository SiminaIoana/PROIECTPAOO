package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL=new URL[30];

    public Sound()
    {
       // soundURL[0]=getClass().getResource("/sound/open_door.wav");
        //coin
        soundURL[1]=getClass().getResource("/sound/coin.wav");
       //fanfare
        soundURL[2]=getClass().getResource("/sound/the-epic.wav");
        soundURL[3]=getClass().getResource("/sound/chipquest.wav");
        soundURL[4]=getClass().getResource("/sound/game_over.wav");

    }

    public void SetFile(int i)
    {
        try{
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e)
        {

        }
    }


    public void Play()
    {
        clip.start();
    }

    public void Loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void Stop()
    {
        clip.stop();
    }
}

