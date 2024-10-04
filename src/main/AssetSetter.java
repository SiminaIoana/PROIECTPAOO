package main;

import entity.Caleb;
import entity.Lupi;
import entity.NPC_Nyx;
import entity.Reptile;
import object.*;


//pentru fiecare harta se deseneaza Entitatile respective hartii
//Aici are loc procesul de creare a tuturor obiectelor si entitailor de pe harta
public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp=gp;

    }

    //instatierea unor obiecte pe harta folosind un vector
    public void setObject()
    {
        int nrMap=0;
        int i=0;

        //10 MONEDE
        //banutii PE PRIMA HARTA->TUNELUL

        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=54*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=30*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx =43*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=19*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=22*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=16*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=55*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=6*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=32*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=25*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=16*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=33*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=40*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=29*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=30*gp.TILE_SIZE;  //
        gp.obj[nrMap][i].Worldy=7*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=55*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=37*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=10*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=35*gp.TILE_SIZE;
        i++;
        //Usa spre tunel
        gp.obj[nrMap][i]=new GoldenBag(gp);
        gp.obj[nrMap][i].Worldx=32*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=36*gp.TILE_SIZE;
        i++;




        //15 monede
        nrMap=1;
        //OBIECTE PENTRU A DOUA HARTA ->PADURE
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=24*gp.TILE_SIZE; ///***
        gp.obj[nrMap][i].Worldy=13*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=11*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=30*gp.TILE_SIZE;
        i++;

        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=49*gp.TILE_SIZE;  //**
        gp.obj[nrMap][i].Worldy=6*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=29*gp.TILE_SIZE;  //**
        gp.obj[nrMap][i].Worldy=6*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);  //***
        gp.obj[nrMap][i].Worldx=8*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=9*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=33*gp.TILE_SIZE;  //**
        gp.obj[nrMap][i].Worldy=17*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=27*gp.TILE_SIZE; //**
        gp.obj[nrMap][i].Worldy=24*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=37*gp.TILE_SIZE; //**
        gp.obj[nrMap][i].Worldy=8*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=38*gp.TILE_SIZE; //**
        gp.obj[nrMap][i].Worldy=38*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=45*gp.TILE_SIZE;  //**
        gp.obj[nrMap][i].Worldy=19*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=46*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=30*gp.TILE_SIZE;  //**
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=18*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=42*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=28*gp.TILE_SIZE;  //**
        gp.obj[nrMap][i].Worldy=34*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=9*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=20*gp.TILE_SIZE;
        i++;
        gp.obj[nrMap][i]=new Coin(gp);
        gp.obj[nrMap][i].Worldx=8*gp.TILE_SIZE;  //**
        gp.obj[nrMap][i].Worldy=30*gp.TILE_SIZE;
        i++;
        //Usa spre tunel
        gp.obj[nrMap][i]=new Door(gp);
        gp.obj[nrMap][i].Worldx=50*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=42*gp.TILE_SIZE;
        i++;


        nrMap=2;
        gp.obj[nrMap][i]=new Diamant(gp);
        gp.obj[nrMap][i].Worldx=47*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=20*gp.TILE_SIZE;
        i++;

        gp.obj[nrMap][i]=new Door2(gp);
        gp.obj[nrMap][i].Worldx=38*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=20*gp.TILE_SIZE;
        i++;

        gp.obj[nrMap][i]=new Comoara(gp);
        gp.obj[nrMap][i].Worldx=9*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=6*gp.TILE_SIZE;
        i++;

        gp.obj[nrMap][i]=new Comoara(gp);
        gp.obj[nrMap][i].Worldx=55*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=6*gp.TILE_SIZE;
        i++;

        gp.obj[nrMap][i]=new Comoara(gp);
        gp.obj[nrMap][i].Worldx=55*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=45*gp.TILE_SIZE;
        i++;

        gp.obj[nrMap][i]=new Comoara(gp);
        gp.obj[nrMap][i].Worldx=9*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=45*gp.TILE_SIZE;
        i++;

        gp.obj[nrMap][i]=new Key(gp);
        gp.obj[nrMap][i].Worldx=27*gp.TILE_SIZE;
        gp.obj[nrMap][i].Worldy=22*gp.TILE_SIZE;
        i++;


    }

    public void setNPC()
    {
        int nrMap=1;
        int i=0;
        gp.npc[nrMap][i]=new NPC_Nyx(gp);
        gp.npc[nrMap][i].Worldx=gp.TILE_SIZE*54;
        gp.npc[nrMap][i].Worldy=gp.TILE_SIZE*43;
        gp.entityList.add(gp.npc[nrMap][i]);
        i++;
    }

    public void setLupi()
    {

        //lupii de pe prima harta
        int nrMap=1;
        int i=0;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*9;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*9;
        gp.entityList.add(gp.lupi[nrMap][i]);
        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*30;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*6;  //**
        gp.entityList.add(gp.lupi[nrMap][i]);
        i++;

        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*30;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*10;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*13;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*31;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*40;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*39;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*20;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*25;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*11;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*11;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*48;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*9;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*55;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*9;
        gp.entityList.add(gp.lupi[nrMap][i]);
        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*38;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*9;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*30;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*37;
        gp.entityList.add(gp.lupi[nrMap][i]);
        ++i;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*50;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*38;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*37;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*7;
        gp.entityList.add(gp.lupi[nrMap][i]);

        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*38;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*40;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*48;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*43;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*20;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*15;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*46;  //**
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*19;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*12;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*30;
        gp.entityList.add(gp.lupi[nrMap][i]);

        i++;
        nrMap=1;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*40;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*37;

        nrMap=2;
        i++;
        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*9;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*9;
        gp.entityList.add(gp.lupi[nrMap][i]);
        i++;


        gp.lupi[nrMap][i]=new Lupi(gp);
        gp.lupi[nrMap][i].Worldx=gp.TILE_SIZE*48;
        gp.lupi[nrMap][i].Worldy=gp.TILE_SIZE*43;
        gp.entityList.add(gp.lupi[nrMap][i]);
        i++;
    }

    public void SetBoss()
    {
        int nrMap=2;
        int  i=0;
        gp.Boss[nrMap][i]=new Caleb(gp);
        gp.Boss[nrMap][i].Worldx=gp.TILE_SIZE*20;
        gp.Boss[nrMap][i].Worldy=gp.TILE_SIZE*20;



    }

    public void setReptile()
    {
        int nrMap=0;
        int i=0;

        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*8;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*6;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*35;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*35;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*39;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*37;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*54;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*37;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*25;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*36;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*30;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*36;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*30;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*28;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*15;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*20;
        gp.entityList.add(gp.reptile[nrMap][i]);

        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*8;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*37;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*38;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*15;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*25;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*13;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*26;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*8;
        gp.entityList.add(gp.reptile[nrMap][i]);

        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*39;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*9;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*49;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*9;
        gp.entityList.add(gp.reptile[nrMap][i]);
        i++;
        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*25;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*38;
        gp.entityList.add(gp.reptile[nrMap][i]);

        gp.reptile[nrMap][i]= new Reptile(gp);
        gp.reptile[nrMap][i].Worldx=gp.TILE_SIZE*55;
        gp.reptile[nrMap][i].Worldy=gp.TILE_SIZE*20;
        gp.entityList.add(gp.reptile[nrMap][i]);

    }
}

