package com.mygdx.game.ui.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.tl.*;
import com.mygdx.game.ui.MyGdxGame;

import java.util.ArrayList;


public class GameScreen implements Screen, InputProcessor {

    //Conexión con el orquestador
    private MyGdxGame parent;

    //screen
    private final Camera camera;
    private final Viewport viewport;
    private Vector3 temp;

    //Gestores
    private static ControllerCelda gestorCelda;
    private static ControllerObserver gestorObserver;
    private static ControllerDado gestorDado;
    private static ControllerPersonaje gestorPersonaje;
    private static ControllerProxy gestorProxy;

    //graphics
    private final SpriteBatch batch;
    private final Stage stage;
    private InputMultiplexer multiplexer;

    //world parameters
    private final int WORLD_WIDTH = 1250;
    private final int WORLD_HEIGHT = 900;

    //background
    Texture background;
    TextureRegion unitFrame;

    //game objects
    private final ArrayList<Celda> tablero;
    private ArrayList<PersonajeAbstracto> personajes1;
    private ArrayList<PersonajeAbstracto> personajes2;


    //players
    String currentPlayer = "blue";

    public TextureRegion summonDie2;
    public TextureRegion summonDie;
    public TextureRegion actionDie;

    //buttons

    ImageButton btnRoll;
    ImageButton btnEndTurn;
    ImageButton btnSummon;
    ImageButton btnAtk;
    ImageButton btnSpAtk;
    ImageButton btnAddChest;
    ImageButton btnSummonType1;
    ImageButton btnSummonType2;
    ImageButton btnSummonType3;
    ImageButton btnUp;
    ImageButton btnDown;
    ImageButton btnLeft;
    ImageButton btnRight;

    //Patterns

    ImageButton btnI;
    ImageButton btnT;
    ImageButton btnL1;
    ImageButton btnL2;
    ImageButton btnZ1;
    ImageButton btnZ2;
    ImageButton btnU;

    //Labels

    Label lTimer;
    Label lTurnPlayer;
    Label chestInfanteria;
    Label chestArtilleria;
    Label chestTanque;
    Label chestAtk;
    Label chestSpAtk;
    Label comm;
    Label lIdCell;
    Label lIdPattern;
    Label lLifeEnemy;
    Label lIdTypeSummon;
    Label lAttack;
    Label lDefense;
    Label lRange;
    Label lSpAttack;
    Label lUnitMove;
    Label lUnitLife;

    //Texture atlas
    public final TextureAtlas diceAtlas = new TextureAtlas("dice/dice.atlas");
    public final TextureAtlas cellAtlas = new TextureAtlas("cells/cells.atlas");
    public final TextureAtlas buttonAtlas = new TextureAtlas("buttons/buttons.atlas");
    public final TextureAtlas patternAtlas = new TextureAtlas("patterns/patterns.atlas");
    public final TextureAtlas personajeAtlas = new TextureAtlas("personajes/personajes.atlas");

    //Variables

    public int gameTime = 60;
    private static int time = 0;
    public int currentCell = 0;
    public int lastEnemySummonCell = 0;
    public int startingCell1;
    public int startingCell2;
    public int idPersonajeSeleccionado = 0;
    private int opponentLife;
    private boolean rolled;
    private boolean movementDice;
    private boolean addedToChest=false;
    private boolean fullChest=false;
    private String currentPattern="I";
    private int currentSummonType=1;

    //variables de movimiento
    private int movementLeft;
    private int movementsMade=0;
    private int maxMoveThisTurn=0;

    //Variables de dados
    int cantDadosArtilleria = 0;
    int cantDadosInfanteria = 0;
    int cantDadosTanque = 0;
    int cantDadosAtk = 0;
    int cantDadosSpAtk = 0;

    int extraDadosArtilleria=0;
    int extraDadosInfanteria=0;
    int extraDadosTanque=0;
    int extraDadosAtk=0;
    int extraDadosSpAtk=0;

    int currentAtk = 0;
    int currentDef = 0;
    int currentRange = 0;
    String currentSpAtk ="";
    int currentUnitLife = 0;
    int currentUnitMove = 0;



    public GameScreen(MyGdxGame myGame) {
        parent = myGame;
        temp = new Vector3();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        // Especificar atlas de texturas

        //Atributos de Gestores
        final int numInicialCeldasNormales = 1;
        final int numInicialCeldasCastillo = 1000;
        final int lifepointsCastillo = 3;


        //Inicializamos los gestores.
        gestorCelda = new ControllerCelda(numInicialCeldasNormales, numInicialCeldasCastillo, lifepointsCastillo);
        gestorObserver = new ControllerObserver(this);
        gestorPersonaje = new ControllerPersonaje();
        gestorProxy = new ControllerProxy(gestorCelda, gestorPersonaje);
        gestorDado = new ControllerDado(this);

        //Inicializar variables
        startingCell1 = gestorCelda.getCellCastleId1();
        startingCell2 = gestorCelda.getCellCastleId2();
        opponentLife = gestorCelda.getCell(startingCell2).getLifePoints();

        //Inicializar background

        background = new Texture("backgrounds/bg.png");
        unitFrame = new TextureRegion(personajeAtlas.findRegion("unitFrame"));

        //Inicializar dados
        summonDie2 = diceAtlas.findRegion("summon");
        summonDie = diceAtlas.findRegion("summon");
        actionDie = diceAtlas.findRegion("movement");


        //Inicializar labels

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/font-export.fnt"));
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font = myFont;

        Label.LabelStyle commStyle = new Label.LabelStyle();
        BitmapFont myFontComm = new BitmapFont(Gdx.files.internal("fonts/font-export.fnt"));
        myFontComm.getData().setScale(0.90f);
        commStyle.fontColor = Color.WHITE;
        commStyle.font = myFontComm;

        Label.LabelStyle labelStyle2 = new Label.LabelStyle();
        BitmapFont myFont2 = new BitmapFont(Gdx.files.internal("fonts/font-export.fnt"));
        myFont2.getData().setScale(0.85f);
        labelStyle2.fontColor = Color.WHITE;
        labelStyle2.font = myFont2;


        /* __________________________________________________________________________________________*/
        /* --------------                         Label ID Cell                              --------*/
        /* ------------------------------------------------------------------------------------------*/

        lIdCell = new Label("", labelStyle2);
        lIdCell.setSize(31, 30);
        lIdCell.setPosition(63, 856);
        lIdCell.setAlignment(Align.center);
        lIdCell.setText(currentCell);


        /* __________________________________________________________________________________________*/
        /* --------------                         Label ID Summon Type                       --------*/
        /* ------------------------------------------------------------------------------------------*/

        lIdTypeSummon = new Label("", labelStyle2);
        lIdTypeSummon.setSize(31, 30);
        lIdTypeSummon.setPosition(160, 856);
        lIdTypeSummon.setAlignment(Align.center);
        lIdTypeSummon.setText(currentSummonType);


        /* __________________________________________________________________________________________*/
        /* --------------                          Label ID Pattern                          --------*/
        /* ------------------------------------------------------------------------------------------*/

        lIdPattern = new Label("", labelStyle2);
        lIdPattern.setSize(31, 30);
        lIdPattern.setPosition(258, 856);
        lIdPattern.setAlignment(Align.center);
        lIdPattern.setText(currentPattern);

        /* __________________________________________________________________________________________*/
        /* --------------                    Label ID Opponent Life                          --------*/
        /* ------------------------------------------------------------------------------------------*/

        lLifeEnemy = new Label("", labelStyle2);
        lLifeEnemy.setSize(31, 30);
        lLifeEnemy.setPosition(360, 856);
        lLifeEnemy.setAlignment(Align.center);
        lLifeEnemy.setText(opponentLife);

        /* __________________________________________________________________________________________*/
        /* --------------                       Label Cronómetro                             --------*/
        /* ------------------------------------------------------------------------------------------*/

        lTimer = new Label("time", labelStyle);
        lTimer.setSize(130, 30);
        lTimer.setPosition(1100, 852);
        lTimer.setAlignment(Align.center);
        lTimer.setText(gameTime);

        /* __________________________________________________________________________________________*/
        /* --------------                      Label Turn Player                             --------*/
        /* ------------------------------------------------------------------------------------------*/

        lTurnPlayer = new Label("Player 1", labelStyle);
        lTurnPlayer.setSize(164, 30);
        lTurnPlayer.setPosition(907, 852);
        lTurnPlayer.setAlignment(Align.center);

        /* __________________________________________________________________________________________*/
        /* --------------                      Labels Chest Stats                            --------*/
        /* ------------------------------------------------------------------------------------------*/

        chestInfanteria = new Label("-1", labelStyle);
        chestInfanteria.setSize(31, 30);
        chestInfanteria.setPosition(1077, 136);
        chestInfanteria.setAlignment(Align.center);

        chestArtilleria = new Label("-1", labelStyle);
        chestArtilleria.setSize(31, 30);
        chestArtilleria.setPosition(1077, 86);
        chestArtilleria.setAlignment(Align.center);

        chestTanque = new Label("-1", labelStyle);
        chestTanque.setSize(31, 30);
        chestTanque.setPosition(1077, 40);
        chestTanque.setAlignment(Align.center);

        chestAtk = new Label("-1", labelStyle);
        chestAtk.setSize(31, 30);
        chestAtk.setPosition(1171, 125);
        chestAtk.setAlignment(Align.center);

        chestSpAtk = new Label("-1", labelStyle);
        chestSpAtk.setSize(31, 30);
        chestSpAtk.setPosition(1171, 58);
        chestSpAtk.setAlignment(Align.center);

        /* __________________________________________________________________________________________*/
        /* --------------                      Labels Unit Stats                            --------*/
        /* ------------------------------------------------------------------------------------------*/


        lUnitLife = new Label("-1", labelStyle2);
        lUnitLife.setSize(31, 30);
        lUnitLife.setPosition(1002, 365);
        lUnitLife.setAlignment(Align.center);
        lUnitLife.setText(currentUnitLife);

        lUnitMove = new Label("-1", labelStyle2);
        lUnitMove.setSize(31, 30);
        lUnitMove.setPosition(1134, 365);
        lUnitMove.setAlignment(Align.center);
        lUnitLife.setText(currentUnitMove);

        lAttack = new Label("-1", labelStyle2);
        lAttack.setSize(31, 30);
        lAttack.setPosition(1002, 314);
        lAttack.setAlignment(Align.center);
        lAttack.setText(currentAtk);

        lDefense = new Label("-1", labelStyle2);
        lDefense.setSize(31, 30);
        lDefense.setPosition(1134, 314);
        lDefense.setAlignment(Align.center);
        lDefense.setText(currentDef);

        lRange = new Label("-1", labelStyle2);
        lRange.setSize(31, 30);
        lRange.setPosition(1002, 264);
        lRange.setAlignment(Align.center);
        lRange.setText(currentRange);

        lSpAttack = new Label("-1", labelStyle2);
        lSpAttack.setSize(31, 30);
        lSpAttack.setPosition(1134, 264);
        lSpAttack.setAlignment(Align.center);
        lSpAttack.setText(currentSpAtk);

        /* __________________________________________________________________________________________*/
        /* --------------                      Label Comm                                    --------*/
        /* ------------------------------------------------------------------------------------------*/

        comm = new Label("", commStyle);
        comm.setSize(164, 30);
        comm.setPosition(475, 40);
        comm.setAlignment(Align.center);


        //Inicializar botones

        /* __________________________________________________________________________________________*/
        /* --------                         Botones de Pad de Dirección                      --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable padUpUp =  new TextureRegionDrawable(personajeAtlas.findRegion("padUpUp"));
        TextureRegionDrawable padUpDown =  new TextureRegionDrawable(personajeAtlas.findRegion("padUpDown"));
        ImageButton.ImageButtonStyle styleUp = new ImageButton.ImageButtonStyle();
        styleUp.up = padUpUp;
        styleUp.down = padUpDown;
        btnUp = new ImageButton(styleUp);
        btnUp.setPosition(1124,481);
        btnUp.addListener(new ClickListener() {
            //TODO validacion Successful move action.
            public void clicked(InputEvent event, float x, float y) {
                if (movementDice) {
                    PersonajeAbstracto gPer = gestorProxy.getInfoPersonaje(idPersonajeSeleccionado, currentPlayer);
                    int unitMaxMove = gPer.getMovimiento();
                    maxMoveThisTurn=Integer.parseInt(actionDie.toString());
                    if(unitMaxMove<maxMoveThisTurn){
                        maxMoveThisTurn=unitMaxMove;
                    }
                    movementLeft = maxMoveThisTurn - movementsMade;

                    if(movementLeft!=0 && movementsMade<maxMoveThisTurn) {
                        String report = gestorProxy.moveUnit(idPersonajeSeleccionado, currentCell, currentPlayer, "up");
                        if (report.equals("ok")) {
                            movementsMade++;
                        }
                        if (report != null) {
                            comm.setText(report);
                        }
                    }else{
                        comm.setText("No more movements available this turn.");
                    }

                    if(movementLeft==0){
                        actionDie=diceAtlas.findRegion("movement");
                        movementDice=false;
                    }
                }else{
                    comm.setText("You do not have a movement die available.");
                }
            }
        });

        TextureRegionDrawable padDownUp =  new TextureRegionDrawable(personajeAtlas.findRegion("padDownUp"));
        TextureRegionDrawable padDownDown =  new TextureRegionDrawable(personajeAtlas.findRegion("padDownDown"));
        ImageButton.ImageButtonStyle styleDown = new ImageButton.ImageButtonStyle();
        styleDown.up = padDownUp;
        styleDown.down = padDownDown;
        btnDown = new ImageButton(styleDown);
        btnDown.setPosition(1125,431);
        btnDown.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                String report = gestorProxy.moveUnit(idPersonajeSeleccionado, currentCell, currentPlayer,"down");
                if (report!=null) {
                    comm.setText(report);
                }
            }
        });

        TextureRegionDrawable padLeftUp =  new TextureRegionDrawable(personajeAtlas.findRegion("padLeftUp"));
        TextureRegionDrawable padLeftDown =  new TextureRegionDrawable(personajeAtlas.findRegion("padLeftDown"));
        ImageButton.ImageButtonStyle styleLeft = new ImageButton.ImageButtonStyle();
        styleLeft.up = padLeftUp;
        styleLeft.down = padLeftDown;
        btnLeft = new ImageButton(styleLeft);
        btnLeft.setPosition(1100,455);
        btnLeft.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                String report = gestorProxy.moveUnit(idPersonajeSeleccionado, currentCell, currentPlayer,"left");
                if (report!=null) {
                    comm.setText(report);
                }
            }
        });

        TextureRegionDrawable padRightUp =  new TextureRegionDrawable(personajeAtlas.findRegion("padRightUp"));
        TextureRegionDrawable padRightDown =  new TextureRegionDrawable(personajeAtlas.findRegion("padRightDown"));
        ImageButton.ImageButtonStyle styleRight = new ImageButton.ImageButtonStyle();
        styleRight.up = padRightUp;
        styleRight.down = padRightDown;
        btnRight = new ImageButton(styleRight);
        btnRight.setPosition(1152,455);
        btnRight.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                String report = gestorProxy.moveUnit(idPersonajeSeleccionado, currentCell, currentPlayer,"right");
                if (report!=null) {
                    comm.setText(report);
                }
            }
        });

        /* __________________________________________________________________________________________*/
        /* --------                              Botones "Summon-type"                       --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable summon1Up =  new TextureRegionDrawable(personajeAtlas.findRegion("summon1Up"));
        TextureRegionDrawable summon1Down =  new TextureRegionDrawable(personajeAtlas.findRegion("summon1Down"));
        ImageButton.ImageButtonStyle styleSummon1 = new ImageButton.ImageButtonStyle();
        styleSummon1.up = summon1Up;
        styleSummon1.down = summon1Down;
        btnSummonType1 = new ImageButton(styleSummon1);
        btnSummonType1.setPosition(969,540);
        btnSummonType1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentSummonType = 1;
                lIdTypeSummon.setText(currentSummonType);
            }
        });

        TextureRegionDrawable summon2Up =  new TextureRegionDrawable(personajeAtlas.findRegion("summon2Up"));
        TextureRegionDrawable summon2Down =  new TextureRegionDrawable(personajeAtlas.findRegion("summon2Down"));
        ImageButton.ImageButtonStyle styleSummon2 = new ImageButton.ImageButtonStyle();
        styleSummon2.up = summon2Up;
        styleSummon2.down = summon2Down;
        btnSummonType2 = new ImageButton(styleSummon2);
        btnSummonType2.setPosition(1047,540);
        btnSummonType2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentSummonType = 2;
                lIdTypeSummon.setText(currentSummonType);
            }
        });

        TextureRegionDrawable summon3Up =  new TextureRegionDrawable(personajeAtlas.findRegion("summon3Up"));
        TextureRegionDrawable summon3Down =  new TextureRegionDrawable(personajeAtlas.findRegion("summon3Down"));
        ImageButton.ImageButtonStyle styleSummon3 = new ImageButton.ImageButtonStyle();
        styleSummon3.up = summon3Up;
        styleSummon3.down = summon3Down;
        btnSummonType3 = new ImageButton(styleSummon3);
        btnSummonType3.setPosition(1125,540);
        btnSummonType3.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentSummonType = 3;
                lIdTypeSummon.setText(currentSummonType);
            }
        });

        /* __________________________________________________________________________________________*/
        /* --------                         Botón de Roll Dados                              --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable rollUpImage =  new TextureRegionDrawable(buttonAtlas.findRegion("rollButtonUp"));
        TextureRegionDrawable rollDownImage =  new TextureRegionDrawable(buttonAtlas.findRegion("rollButtonDown"));
        ImageButton.ImageButtonStyle styleRoll = new ImageButton.ImageButtonStyle();
        styleRoll.up = rollUpImage;
        styleRoll.down = rollDownImage;
        btnRoll = new ImageButton(styleRoll);
        btnRoll.setPosition(75,40);
        btnRoll.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                if(!rolled) {
                    comm.setText("");
                    gestorDado.rodarDado();
                    rolled=true;
                }else{
                    comm.setText("You can only roll the dice once per turn.");
                }
            }
        });


        /* __________________________________________________________________________________________*/
        /* -----------                            Botón de Ataque                            --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable atkUp =  new TextureRegionDrawable(buttonAtlas.findRegion("atkUp"));
        TextureRegionDrawable atkDown =  new TextureRegionDrawable(buttonAtlas.findRegion("atkDown"));
        ImageButton.ImageButtonStyle styleAtk = new ImageButton.ImageButtonStyle();
        styleAtk.up = atkUp;
        styleAtk.down = atkDown;
        btnAtk = new ImageButton(styleAtk);
        btnAtk.setPosition(939,750);
        btnAtk.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                comm.setText("Action activated: ATTACK.");
            }
        });

        /* __________________________________________________________________________________________*/
        /* -----------                          Botón de Ataque Especial                     --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable spAtkUp =  new TextureRegionDrawable(buttonAtlas.findRegion("spAtkUp"));
        TextureRegionDrawable spAtkDown =  new TextureRegionDrawable(buttonAtlas.findRegion("spAtkDown"));
        ImageButton.ImageButtonStyle styleSpAtk = new ImageButton.ImageButtonStyle();
        styleSpAtk.up = spAtkUp;
        styleSpAtk.down = spAtkDown;
        btnSpAtk = new ImageButton(styleSpAtk);
        btnSpAtk.setPosition(1083,750);
        btnSpAtk.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                //todo: validaciones
                if(cantDadosSpAtk>0){
                    if(gestorPersonaje.retornarPersonajeDecorador(idPersonajeSeleccionado).getAtaqueEspecial().equals("")){
                        comm.setText("SP ATTACK: already used");
                    } else{
                        if(personajes1.size()>1 && personajes2.size()>1){
                            gestorPersonaje.aplicarAtaqueEspecial(idPersonajeSeleccionado);
                            System.out.println(gestorPersonaje.retornarPersonajeDecorador(idPersonajeSeleccionado));
                            comm.setText("Action activated: SP ATTACK.");
                            gestorPersonaje.eliminarAtaqueSP(idPersonajeSeleccionado);
                            //System.out.println(gestorPersonaje.retornarPersonajeDecorador(idPersonajeSeleccionado).obtenerInformacionPersonaje());
                            cantDadosSpAtk--; // todo: restarle al rol del turno
                        } else {
                            //no tendria sentido aplicar un sp attack si solo hay una persona en el juego
                            comm.setText("there aren't enough units");
                        }
                    }
                } else{
                    comm.setText("there aren't enough SP ATTACK dice");
                }
            }
        });


        /* __________________________________________________________________________________________*/
        /* -----------                         Botón de End Turn                             --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable endTurnUp =  new TextureRegionDrawable(buttonAtlas.findRegion("endTurnUp"));
        TextureRegionDrawable endTurnDown =  new TextureRegionDrawable(buttonAtlas.findRegion("endTurnDown"));
        ImageButton.ImageButtonStyle styleEnd = new ImageButton.ImageButtonStyle();
        styleEnd.up = endTurnUp;
        styleEnd.down = endTurnDown;
        btnEndTurn = new ImageButton(styleEnd);
        btnEndTurn.setPosition(1083,690);
        btnEndTurn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                comm.setText("");
                addedToChest=false;
                extraDadosArtilleria=0;
                extraDadosInfanteria=0;
                extraDadosTanque=0;
                extraDadosAtk=0;
                extraDadosSpAtk=0;
                movementsMade=0;
                endTurn();
            }
        });

        /* __________________________________________________________________________________________*/
        /* -----------                         Botón de Summon                               --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable summonUp =  new TextureRegionDrawable(buttonAtlas.findRegion("summonUp"));
        TextureRegionDrawable summonDown =  new TextureRegionDrawable(buttonAtlas.findRegion("summonDown"));
        ImageButton.ImageButtonStyle styleSummon = new ImageButton.ImageButtonStyle();
        styleSummon.up = summonUp;
        styleSummon.down = summonDown;
        btnSummon = new ImageButton(styleSummon);
        btnSummon.setPosition(939,690);
        btnSummon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
               startSummoning();
            }});



        /* __________________________________________________________________________________________*/
        /* -----------                        Botón de Add To Chest                             --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable addUp =  new TextureRegionDrawable(buttonAtlas.findRegion("addUp"));
        final TextureRegionDrawable addDown =  new TextureRegionDrawable(buttonAtlas.findRegion("addDown"));
        ImageButton.ImageButtonStyle styleAdd = new ImageButton.ImageButtonStyle();
        styleAdd.up = addUp;
        styleAdd.down = addDown;
        btnAddChest = new ImageButton(styleAdd);
        btnAddChest.setPosition(929,122);
        btnAddChest.addListener(new ClickListener() {
            boolean added;
            public void clicked(InputEvent event, float x, float y){
                int cont;
                if(currentPlayer.equals("blue") && !addedToChest) {
                    if(extraDadosInfanteria>0) {
                        cont=0;
                        for (int i = 0; i < extraDadosInfanteria; i++) {
                            added = gestorDado.addToChest(1);
                            if (added) {
                                cont++;
                            } else {
                                comm.setText("Error: Chest is full. At least 1 die was not saved.");
                                addedToChest=false;
                            }
                        }

                        for (int i = 0; i < cont; i++) {
                            if (summonDie.toString().equals("infanteria")) {
                                summonDie = diceAtlas.findRegion("summon");
                                extraDadosInfanteria--;
                            } else if (summonDie2.toString().equals("infanteria")) {
                                summonDie2 = diceAtlas.findRegion("summon");
                                extraDadosInfanteria--;
                            }
                        }
                        addedToChest=true;
                    }

                    if(extraDadosArtilleria>0) {
                        cont=0;
                        for (int i = 0; i < extraDadosArtilleria; i++) {
                            added = gestorDado.addToChest(2);
                            if (added) {
                                cont++;
                            } else {
                                comm.setText("Error: Chest is full. At least 1 die was not saved.");
                                addedToChest=false;
                            }
                        }

                        for (int i = 0; i < cont; i++) {
                            if (summonDie.toString().equals("artilleria")) {
                                summonDie = diceAtlas.findRegion("summon");
                                extraDadosArtilleria--;
                            } else if (summonDie2.toString().equals("artilleria")) {
                                summonDie2 = diceAtlas.findRegion("summon");
                                extraDadosArtilleria--;
                            }
                        }
                        addedToChest=true;
                    }

                    if(extraDadosTanque>0) {
                        cont=0;
                        for (int i = 0; i < extraDadosTanque; i++) {
                            added = gestorDado.addToChest(3);
                            if (added) {
                                cont++;
                            } else {
                                comm.setText("Error: Chest is full. At least 1 die was not saved.");
                                addedToChest=false;
                            }
                        }

                        for (int i = 0; i < cont; i++) {
                            if (summonDie.toString().equals("tanque")) {
                                summonDie = diceAtlas.findRegion("summon");
                                extraDadosTanque--;
                            } else if (summonDie2.toString().equals("tanque")) {
                                summonDie2 = diceAtlas.findRegion("summon");
                                extraDadosTanque--;
                            }
                        }
                        addedToChest=true;
                    }

                    if(!movementDice){
                        //TODO talvez a futuro implementacion para quitar esto si se usa?
                        if(actionDie.toString().equals("Ataque")){
                            added=gestorDado.addToChest(4);
                        }else{
                            added=gestorDado.addToChest(5);
                        }
                        if(added){
                            actionDie = diceAtlas.findRegion("movement");
                        }
                    }

                    if(!fullChest){
                        comm.setText("Dice saved to chest successfully.");
                    }

                    if(!addedToChest){
                        comm.setText("There are no dice to save.");
                    }

                }else{
                    if(!fullChest) {
                        comm.setText("You've already put your dice in the chest this turn.");
                    }
                }
            }});

        //Inicializar patrones

        /* __________________________________________________________________________________________*/
        /* --------                           Botón de Patrón I                              --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable iUp =  new TextureRegionDrawable(patternAtlas.findRegion("iUp"));
        TextureRegionDrawable iDown =  new TextureRegionDrawable(patternAtlas.findRegion("iDown"));
        ImageButton.ImageButtonStyle iStyle = new ImageButton.ImageButtonStyle();
        iStyle.up = iUp;
        iStyle.down = iDown;
        btnI = new ImageButton(iStyle);
        btnI.setPosition(60,680);
        btnI.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentPattern = "I";
                lIdPattern.setText(currentPattern);
            }
        });

        /* __________________________________________________________________________________________*/
        /* --------                           Botón de Patrón T                              --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable tUp =  new TextureRegionDrawable(patternAtlas.findRegion("tUp"));
        TextureRegionDrawable tDown =  new TextureRegionDrawable(patternAtlas.findRegion("tDown"));
        ImageButton.ImageButtonStyle tStyle = new ImageButton.ImageButtonStyle();
        tStyle.up = tUp;
        tStyle.down = tDown;
        btnT = new ImageButton(tStyle);
        btnT.setPosition(134,695);
        btnT.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentPattern = "T";
                lIdPattern.setText(currentPattern);
            }
        });

        /* __________________________________________________________________________________________*/
        /* --------                           Botón de Patrón L1                             --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable l1Up =  new TextureRegionDrawable(patternAtlas.findRegion("l1Up"));
        TextureRegionDrawable l1Down =  new TextureRegionDrawable(patternAtlas.findRegion("l1Down"));
        ImageButton.ImageButtonStyle l1Style = new ImageButton.ImageButtonStyle();
        l1Style.up = l1Up;
        l1Style.down = l1Down;
        btnL1 = new ImageButton(l1Style);
        btnL1.setPosition(60,583);
        btnL1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentPattern = "L1";
                lIdPattern.setText(currentPattern);
            }
        });

        /* __________________________________________________________________________________________*/
        /* --------                           Botón de Patrón L2                             --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable l2Up =  new TextureRegionDrawable(patternAtlas.findRegion("l2Up"));
        TextureRegionDrawable l2Down =  new TextureRegionDrawable(patternAtlas.findRegion("l2Down"));
        ImageButton.ImageButtonStyle l2Style = new ImageButton.ImageButtonStyle();
        l2Style.up = l2Up;
        l2Style.down = l2Down;
        btnL2 = new ImageButton(l2Style);
        btnL2.setPosition(134,583);
        btnL2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentPattern = "L2";
                lIdPattern.setText(currentPattern);
            }
        });

        /* __________________________________________________________________________________________*/
        /* --------                           Botón de Patrón Z1                             --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable z1Up =  new TextureRegionDrawable(patternAtlas.findRegion("z1Up"));
        TextureRegionDrawable z1Down =  new TextureRegionDrawable(patternAtlas.findRegion("z1Down"));
        ImageButton.ImageButtonStyle z1Style = new ImageButton.ImageButtonStyle();
        z1Style.up = z1Up;
        z1Style.down = z1Down;
        btnZ1 = new ImageButton(z1Style);
        btnZ1.setPosition(60,480);
        btnZ1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentPattern = "Z1";
                lIdPattern.setText(currentPattern);
            }
        });

        /* __________________________________________________________________________________________*/
        /* --------                           Botón de Patrón Z2                             --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable z2Up =  new TextureRegionDrawable(patternAtlas.findRegion("z2Up"));
        TextureRegionDrawable z2Down =  new TextureRegionDrawable(patternAtlas.findRegion("z2Down"));
        ImageButton.ImageButtonStyle z2Style = new ImageButton.ImageButtonStyle();
        z2Style.up = z2Up;
        z2Style.down = z2Down;
        btnZ2 = new ImageButton(z2Style);
        btnZ2.setPosition(134,480);
        btnZ2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentPattern = "Z2";
                lIdPattern.setText(currentPattern);
            }
        });

        /* __________________________________________________________________________________________*/
        /* --------                           Botón de Patrón U                              --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable uUp =  new TextureRegionDrawable(patternAtlas.findRegion("uUp"));
        TextureRegionDrawable uDown =  new TextureRegionDrawable(patternAtlas.findRegion("uDown"));
        ImageButton.ImageButtonStyle uStyle = new ImageButton.ImageButtonStyle();
        uStyle.up = uUp;
        uStyle.down = uDown;
        btnU = new ImageButton(uStyle);
        btnU.setPosition(92,395);
        btnU.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                currentPattern = "U";
                lIdPattern.setText(currentPattern);
            }
        });


        //Establecer objetos de juego
        tablero = gestorCelda.getCellArray();
        personajes1 = gestorPersonaje.getArrayPersonajes();
        personajes2 = gestorPersonaje.getArrayEnemigos();


        //setear la pantalla al input processor para responder a clicks

        multiplexer = new InputMultiplexer();
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);

        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);

        //add actors to stage

        stage.addActor(btnRoll);
        stage.addActor(btnEndTurn);
        stage.addActor(btnAddChest);
        stage.addActor(btnSummon);
        stage.addActor(btnAtk);
        stage.addActor(btnSpAtk);
        stage.addActor(lTimer);
        stage.addActor(lTurnPlayer);
        stage.addActor(chestInfanteria);
        stage.addActor(chestArtilleria);
        stage.addActor(chestTanque);
        stage.addActor(chestAtk);
        stage.addActor(chestSpAtk);
        stage.addActor(comm);
        stage.addActor(btnI);
        stage.addActor(btnT);
        stage.addActor(btnL1);
        stage.addActor(btnL2);
        stage.addActor(btnZ1);
        stage.addActor(btnZ2);
        stage.addActor(btnU);
        stage.addActor(lIdCell);
        stage.addActor(lIdTypeSummon);
        stage.addActor(lIdPattern);
        stage.addActor(lLifeEnemy);
        stage.addActor(lAttack);
        stage.addActor(lSpAttack);
        stage.addActor(lUnitLife);
        stage.addActor(lUnitMove);
        stage.addActor(lRange);
        stage.addActor(lDefense);
        stage.addActor(btnSummonType1);
        stage.addActor(btnSummonType2);
        stage.addActor(btnSummonType3);
        stage.addActor(btnUp);
        stage.addActor(btnDown);
        stage.addActor(btnLeft);
        stage.addActor(btnRight);
    }


    @Override
    public void render(float deltaTime) {
        batch.begin();

        //Dibujar background
        batch.draw(background, 0,0);
        batch.draw(unitFrame, 963, 417);

        //Dibujar Tablero
        for (Celda c:tablero) {
            c.draw(batch);
        }

        if (personajes1!=null) {
            for (PersonajeAbstracto p : personajes1) {
                p.draw(batch);
            }
        }


        if (personajes2!=null) {
            for (PersonajeAbstracto p2 : personajes2) {
                p2.draw(batch);
            }
        }
      
        //dibuja labels dinamicas
        lTimer.setText(gameTime);
        chestInfanteria.setText(cantDadosInfanteria);
        chestArtilleria.setText(cantDadosArtilleria);
        chestTanque.setText(cantDadosTanque);
        chestAtk.setText(cantDadosAtk);
        chestSpAtk.setText(cantDadosSpAtk);

        batch.draw(summonDie, 52,152);
        batch.draw(summonDie2, 136,152);
        batch.draw(actionDie, 92,87);


        batch.end();
        stage.act();
        stage.draw();

    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX, screenY, 0);
        camera.unproject(temp);

        for (Celda c : tablero) {
            if (c.getBoundingBox().contains(temp.x,temp.y)) {
                currentCell = c.getId();
                lIdCell.setText(currentCell);
            }
        }

        for (PersonajeAbstracto p : personajes1) {
            if (p.getRectangle().contains(temp.x,temp.y)) {
                idPersonajeSeleccionado = p.getIdPersonaje();
                PersonajeAbstracto pShow= gestorProxy.getInfoPersonaje(idPersonajeSeleccionado, currentPlayer);
                if (pShow!=null) {
                    currentAtk = pShow.getAtaque();
                    lAttack.setText(currentAtk);
                    currentDef = pShow.getDefensa();
                    lDefense.setText(currentDef);
                    currentSpAtk = renderSpecialAttack(pShow.getAtaqueEspecial());
                    lSpAttack.setText(currentSpAtk);
                    currentRange = pShow.getRango();
                    lRange.setText(currentRange);
                    currentUnitMove = pShow.getMovimiento();
                    lUnitMove.setText(currentUnitMove);
                    currentUnitLife = pShow.getVida();
                    lUnitLife.setText(currentUnitLife);
                    unitFrame = pShow.gettRegion();
                }
                else {
                    comm.setText("Current turn player do not own that unit.");
                }
            }
        }

        for (PersonajeAbstracto p : personajes2) {
            if (p.getRectangle().contains(temp.x,temp.y)) {
                idPersonajeSeleccionado = p.getIdPersonaje();
                PersonajeAbstracto pShow= gestorProxy.getInfoPersonaje(idPersonajeSeleccionado, currentPlayer);
                if (pShow!=null) {
                    currentAtk = pShow.getAtaque();
                    lAttack.setText(currentAtk);
                    currentDef = pShow.getDefensa();
                    lDefense.setText(currentDef);
                    currentSpAtk = renderSpecialAttack(pShow.getAtaqueEspecial());
                    lSpAttack.setText(currentSpAtk);
                    currentRange = pShow.getRango();
                    lRange.setText(currentRange);
                    currentUnitMove = pShow.getMovimiento();
                    lUnitMove.setText(currentUnitMove);
                    currentUnitLife = pShow.getVida();
                    lUnitLife.setText(currentUnitLife);
                    unitFrame = pShow.gettRegion();
                }
                else {
                    comm.setText("Current turn player does not own that unit.");
                }
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public void endTurn(){
        changeTurn();
        gestorObserver.resetTimer();
        if(currentPlayer.equals("red")){
            startSummoning();
        }
    }

    public void changeTurn(){
        if(currentPlayer.equals("blue")){
            currentPlayer="red";
        }
        else if(currentPlayer.equals("red")){
            currentPlayer="blue";
            rolled=false;
        }

        if (currentPlayer.equals("blue") ) {
            lTurnPlayer.setText("Player 1");
        }else{
            lTurnPlayer.setText("Player 2");
        }
    }

    public void updateClock(int value){
        gameTime=value;
    }

    public void renderDice(String rollInvo, String rollInvo2, String rollAccion){
        switch (rollInvo) {
            case "Infanteria":
                summonDie = diceAtlas.findRegion("infanteria");
                break;
            case "Artilleria":
                summonDie = diceAtlas.findRegion("artilleria");
                break;
            case "Tanque":
                summonDie = diceAtlas.findRegion("tanque");
                break;
        }

        switch(rollInvo2){
            case "Infanteria":
                summonDie2 = diceAtlas.findRegion("infanteria");
                break;
            case "Artilleria":
                summonDie2 = diceAtlas.findRegion("artilleria");
                break;
            case "Tanque":
                summonDie2 = diceAtlas.findRegion("tanque");
                break;
            default:
                summonDie2 = diceAtlas.findRegion("summon");
                break;
        }

        if (rollAccion.equals("Ataque")){
            actionDie = diceAtlas.findRegion("atk");
            movementDice=false;
        }else if (rollAccion.equals("AtaqueEspecial")){
            actionDie = diceAtlas.findRegion("spatk");
            movementDice=false;
        }else{
            movementDice=true;
            int movimiento = Integer.parseInt(rollAccion);
            for (int i=0;i<6;i++){
                if(i==movimiento){
                    actionDie = diceAtlas.findRegion(String.valueOf(i));
                }
            }
        }
    }

    public void updateChest() {
        int[] dice = gestorDado.savedDice();
        cantDadosInfanteria=dice[0];
        cantDadosArtilleria=dice[1];
        cantDadosTanque=dice[2];
        cantDadosAtk=dice[3];
        cantDadosSpAtk=dice[4];
    }

    public void setRolledDice(String proll1, String proll2, String proll3) {
        switch (proll1) {
            case "Infanteria":
                extraDadosInfanteria++;
                break;
            case "Artilleria":
                extraDadosArtilleria++;
                break;
            case "Tanque":
                extraDadosTanque++;
                break;
        }

        switch (proll2) {
            case "Infanteria":
                extraDadosInfanteria++;
                break;
            case "Artilleria":
                extraDadosArtilleria++;
                break;
            case "Tanque":
                extraDadosTanque++;
                break;
        }
        if(proll3!=null) {
            if (proll3.equals("Ataque")) {
                extraDadosAtk++;
            } else {
                extraDadosSpAtk++;
            }
        }
    }

    public String renderSpecialAttack(String type) {
        String renderType;
        switch (type) {
            case "healer1":
                renderType = "H1";
                break;
            case "healer2":
                renderType = "H2";
                break;
            case "unaVidaDobleM":
                renderType = "+M";
                break;
            case "sumar3Ataque":
            case "doblePoderAtaque":
                renderType = "+A";
                break;
            case "ataqueDosCasillas": //significa que tiene mas rango
                renderType = "+R";
                break;
            case "sumar3Defensa":
                renderType = "3D";
                break;
            case "doblePoderDefensa":
                renderType = "2D";
                break;
            case "proteccionAliade":
                renderType = "PA";
                break;
            case "bajarDefensa":
            case "bajar2Defensa":
                renderType = "-D";
                break;
            case "ataqueBomba":
                renderType="AB";
                break;
            default:
                renderType = "";
                break;
        }
        return renderType;
    }

    public void full() {
        fullChest=true;
    }

    public boolean canSummon(int ptipo){

        boolean canSummon=false;
        if(ptipo==1){
            if(cantDadosInfanteria+extraDadosInfanteria>=2){
                canSummon=true;
            }
        }else if(ptipo==2){
            if(cantDadosArtilleria+extraDadosArtilleria>=3){
                canSummon=true;
            }
        }else{
            if(cantDadosTanque+extraDadosTanque>=4){
                canSummon=true;
            }
        }

        return canSummon;
    }

    public void startSummoning() {
        String report;
        if (currentPlayer.equals("blue")) {
            if(canSummon(currentSummonType)) {
                if (currentCell == 0) {
                    report = gestorProxy.startSummon(startingCell1, currentPattern, currentPlayer, currentSummonType);
                } else {
                    report = gestorProxy.startSummon(currentCell, currentPattern, currentPlayer, currentSummonType);
                }
                if (report.equals("Summoning successful.")) {
                    fullChest=false;
                    gestorDado.summon(currentSummonType);
                    switch (currentSummonType) {
                        case 1:
                            if(extraDadosInfanteria!=0) {
                                int tam = extraDadosInfanteria;
                                for (int i = 0; i < tam; i++) {
                                    if (summonDie.toString().equals("infanteria")) {
                                        summonDie = diceAtlas.findRegion("summon");
                                        extraDadosInfanteria--;
                                    } else if (summonDie2.toString().equals("infanteria")) {
                                        summonDie2 = diceAtlas.findRegion("summon");
                                        extraDadosInfanteria--;
                                    }
                                }
                            }
                            break;
                        case 2:
                            if(extraDadosArtilleria!=0){
                                int tam = extraDadosArtilleria;
                                for(int i=0; i<tam;i++){
                                    if (summonDie.toString().equals("artilleria")) {
                                        summonDie = diceAtlas.findRegion("summon");
                                        extraDadosArtilleria--;
                                    } else if (summonDie2.toString().equals("artilleria")) {
                                        summonDie2 = diceAtlas.findRegion("summon");
                                        extraDadosArtilleria--;
                                    }
                                }
                            }
                            break;
                        case 3:
                            if(extraDadosTanque!=0){
                                int tam = extraDadosTanque;
                                for(int i=0; i<tam;i++){
                                    if (summonDie.toString().equals("tanque")) {
                                        summonDie = diceAtlas.findRegion("summon");
                                        extraDadosTanque--;
                                    } else if (summonDie2.toString().equals("tanque")) {
                                        summonDie2 = diceAtlas.findRegion("summon");
                                        extraDadosTanque--;
                                    }
                                }
                            }
                            break;
                    }
                    fullChest=false;
                }
            }else{
                report = "Not enough dice to summon that unit.";
            }
            updateChest();
        }else{
            if (lastEnemySummonCell==0) {
                report = gestorProxy.startSummon(startingCell2, "T", currentPlayer, currentSummonType);
                lastEnemySummonCell=gestorCelda.getLastEnemySummonCell();
            }else {
                do {
                    report = gestorProxy.startSummon(lastEnemySummonCell, getRandomPattern(), currentPlayer, currentSummonType);
                }while(!report.equals("Summoning successful."));
            }
            comm.setText(report);
            lastEnemySummonCell=gestorCelda.getLastEnemySummonCell();
            //TODO revertir esto a 15
            float delay=1;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    comm.setText("Processing...");
                    endTurn();
                }
            },delay);
        }
        comm.setText(report);
        updateChest();
    }

    public String getRandomPattern(){
        int rnd = (int) ((Math.random()*12)+1);
        String prefa="";
        switch(rnd){
            case 1:
                prefa="I";
                break;
            case 2:
                prefa="L1";
                break;
            case 3:
                prefa="L2";
                break;
            case 4:
                prefa="Z1";
                break;
            case 5:
                prefa="Z2";
                break;
            default:
                prefa="T";
                break;
        }
        return prefa;
    }

}


