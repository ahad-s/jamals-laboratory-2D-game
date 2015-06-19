import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;


//IMPLEMENTS ACTIONLISTENER/KEYLISTENER FOR USE OF KEYBOARD AND ACTIONS THAT OCCUR
//AS A RESULT INSIDE ONE SINGLE JPANEL
public class Game extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = -4333792986551424731L;

    static final int HEIGHT = 768;
    static final int WIDTH = 1024;


    public boolean jump;

    //IMPORTS OF ALL IMAGES USED IN GAME

    ImageIcon room1 = new ImageIcon("src/ISU/room1copy.jpg");
    ImageIcon mario = new ImageIcon("src/ISU/marioRIGHT.png");
    ImageIcon puzzle1 = new ImageIcon("src/ISU/scientistscopy.jpg");
    ImageIcon startscreenImage = new ImageIcon("src/isu/marioloadscreen_noMario.png");
    ImageIcon instructions = new ImageIcon("src/isu/instructions.png");

    ImageIcon kappa = new ImageIcon("src/isu/kappa.png");
    ImageIcon kappacry = new ImageIcon("src/isu/kappaCRY.png");
    ImageIcon biblethump = new ImageIcon("src/isu/twitch/biblethump.png");
    ImageIcon kippa = new ImageIcon("src/isu/twitch/kippa.png");
    ImageIcon pjsalt = new ImageIcon("src/isu/twitch/pjsalt.png");
    ImageIcon brainslug = new ImageIcon("src/isu/twitch/brainslug.png");
    ImageIcon lirikthump = new ImageIcon("src/isu/twitch/lirikthump.png");
    ImageIcon bloodtrail = new ImageIcon("src/isu/twitch/bloodtrail.png");
    ImageIcon kapow = new ImageIcon("src/isu/twitch/kapow.png");

    ImageIcon door1 = new ImageIcon("src/isu/mario_door.png");
    ImageIcon floor = new ImageIcon("src/isu/floor.png");

    ImageIcon frankerz = new ImageIcon("src/isu/frankerz.png");
    ImageIcon spikes = new ImageIcon("src/isu/spikes.png");

    ImageIcon room3bk = new ImageIcon("src/isu/room3bkONEFLOOR.png");
    ImageIcon ladder1 = new ImageIcon("src/isu/ladder1.png");

    ImageIcon grave = new ImageIcon("src/isu/mario_grave.png");
    ImageIcon end = new ImageIcon("src/isu/thanksend.png");

    ImageIcon life5 = new ImageIcon("src/isu/lives 0-5.png");
    ImageIcon life10 = new ImageIcon("src/isu/lives 1.png");
    ImageIcon life15 = new ImageIcon("src/isu/lives 1-5.png");
    ImageIcon life20 = new ImageIcon("src/isu/lives 2.png");
    ImageIcon life25 = new ImageIcon("src/isu/lives 2-5.png");
    ImageIcon life30 = new ImageIcon("src/isu/lives.png");


    int maxX = WIDTH - mario.getIconWidth() - 10;
    int maxY = HEIGHT - mario.getIconHeight() - mario.getIconHeight() / 2;


    private int time_int = 2;

    Timer tm = new Timer(time_int, this);

    int doorx = WIDTH - 160, doory = HEIGHT - 230;


    //CHARACTER PHYSICS DECLARATIONS
    static double x = WIDTH / 2;
    static double y = HEIGHT - 155;

    double gravity = 10;

    double velY = 0, velX = 0;

    private static int lives = 6;

    private double vel = 1;

    private int airtime = 1;
    private double time = airtime / 1000;
    private double velJump = 2;
    private double jumpMax = 416;
    private double velFall = 2;
    private double groundY = HEIGHT - 190;


    //SET UP FOR COLLISION DETECTION AND OBJECT PLACEMENT

    int px1 = 600;
    int py1 = 140;

    int puzz1x1 = px1 - (puzzle1.getIconWidth() / 2) + mario.getIconWidth();
    int puzz1x2 = puzz1x1 + puzzle1.getIconWidth() + mario.getIconWidth();
    int puzz1y1 = py1 - (puzzle1.getIconHeight() / 2);
    int puzz1y2 = puzz1y1 + puzzle1.getIconHeight() + mario.getIconHeight();


    double[] recY = new double[7];
    double[] recX = new double[7];

    int whiteRecX1 = 550;
    int whiteRecX2 = 550 + spikes.getIconWidth();
    int whiteRecY1 = HEIGHT - 190;

    //CONTROL BOOLEANS FOR EACHR OOM

    boolean startroom = true;
    boolean room1on = false;
    boolean puzz1control = false;
    boolean puzz1controlTEXT = false;
    boolean puzz2control = false;
    boolean puzz3control = false;
    boolean endscreen = false;
    boolean dead = false;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //CONTROL BOOLEANS FOR COLLISION DETECTION + RANDOM DECLARATIONS

    boolean door1text = false; //changes to true if mario touches door and presses space
    boolean door1textb = true; //keep this true before starting program

    Font testfont = new Font("Comic sans ms", Font.ITALIC, 30);
    Font door1font = new Font("Comic sans ms", Font.ITALIC, 14);
    Font mariofont = new Font("Cooper Std Black", Font.BOLD, 35);

    int kapWidth = kappa.getIconWidth();
    int frankWidth = frankerz.getIconWidth();

    boolean kippaB = false;
    boolean biblethumpB = false;
    boolean pjsaltB = false;
    boolean brainslugB = false;
    boolean bloodtrailB = false;
    boolean kapowB = false;
    boolean lirikthumpB = false;

    static boolean right;
    static boolean left;
    static boolean spacePressed;
    static boolean controlallowed = true;

    static int menuchoice = 2;


    private static boolean airbourneDown = false;
    public static boolean airbourne = false;

    public static int spikes1x = 300;
    public static int spikes2x = 100;

    static int spikes1y = HEIGHT - 138;
    static int spikes2y = HEIGHT - 138;

    int spikes1x1 = spikes1x;
    int spikes1x2 = spikes1x + spikes.getIconWidth() - mario.getIconWidth();
    int spikes1y1 = spikes1y;
    int spikes1y2 = spikes1y + spikes.getIconHeight();

    int spikes2x1 = spikes2x;
    int spikes2x2 = spikes2x + spikes.getIconWidth() - mario.getIconWidth();
    int spikes2y1 = spikes2y;
    int spikes2y2 = spikes2y - spikes.getIconHeight();


    int ladder1x = 282;
    int ladder1y = 393;


    boolean onladder1 = false;
    boolean on2ndlevel = false;
    boolean leftLadder = false;
    boolean cantBack = false;
    boolean instructionB = false;


    //MAIN CLASS OBJECT

    public Game() {
        tm.start(); //start timer
        addKeyListener(this); //this refers to KeyListener b/c its a method
        setFocusable(true);
        setFocusTraversalKeysEnabled(false); //because not using shift or tab key

        recX[0] = kappa.getIconWidth() / 2 + kappa.getIconWidth();
        recX[1] = kappa.getIconWidth() / 2 + kappa.getIconWidth() * 2;

        for (int i = 0; i < recY.length; i++){
            recY[i] = 30 + kappa.getIconHeight();
        }

        for (int i = 2; i < recY.length; i++){
            recX[i] = (kappa.getIconWidth() / 2) + kappa.getIconWidth() * (i + 1);
        }

    }

    //THIS METHOD CHECKS WHICH ROOM IS CURRENTLY TRUE AND DISPLAYS CORROSPONDING IMAGES

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color menu1 = Color.yellow;
        Color menu2 = Color.red;


        if (instructionB) {
            instructions.paintIcon(null, g, 0, 0);
            //WAITS 6 SECONDS OR USER PRESSES SPACE TO ADVANCE
            new newTimer2(6);
            if (newTimer2.count >= 6 || c == KeyEvent.VK_SPACE) {
                instructionB = false;
                room1on = true;
            }
        }

        //KEEPS COLOUR RED IF THE CHOICE ISN'T SELECTED, AND YELLOW IF IT IS, CONNECTED TO KEYLISTENER
        if (startroom) {
            startscreenImage.paintIcon(null, g, 0, 0);
            mario.paintIcon(null, g, (int) x, (int) y);

            g.setFont(mariofont);

            if (menuchoice == 2) {
                g.setColor(menu1);
                if (c == KeyEvent.VK_ENTER) {
                    startroom = false;
                    instructionB = true;
                    x = WIDTH / 2;
                    y = HEIGHT - mario.getIconHeight();
                }
            } else
                g.setColor(menu2);
            g.drawString("START GAME", WIDTH / 2 - 116, 400);

            if (menuchoice == 1) {
                g.setColor(menu1);
                if (c == KeyEvent.VK_ENTER) {
                    System.exit(0);
                }
            } else
                g.setColor(menu2);


            g.drawString("EXIT", WIDTH / 2 - 40, 450);

        }

        if (puzz1control) {
            kippaB = true;
            biblethumpB = true;
            pjsaltB = true;
            brainslugB = true;
            bloodtrailB = true;
            kapowB = true;
            lirikthumpB = true;

        }
        if (puzz3control) {
            room3bk.paintIcon(null, g, 0, 0);
            ladder1.paintIcon(null, g, ladder1x, ladder1y);
            if (cantBack) {
                g.setColor(Color.blue);
                g.setFont(door1font);
                g.drawString("Your tiny legs", ladder1x, ladder1y - 100);
                g.drawString("cant climb", ladder1x, ladder1y - 80);
                g.drawString("down the", ladder1x, ladder1y - 60);
                g.drawString("ladder.", ladder1x, ladder1y - 40);
            }
            cantBack = false;

        }

        if (room1on) {
            room1.paintIcon(this, g, 0, 0);
            puzzle1.paintIcon(this, g, px1, py1);
            mario.paintIcon(this, g, (int) x, (int) y);
        } else if ((!room1on && puzz1control) || (!room1on && puzz2control || !room1on && puzz3control)) {
            if (puzz1control) {
                door1.paintIcon(null, g, doorx, doory);
                floor.paintIcon(null, g, 0, HEIGHT - 190 + mario.getIconHeight());
            }
            if (puzz2control) {
                door1.paintIcon(null, g, doorx, doory);
                floor.paintIcon(null, g, 0, HEIGHT - 190 + mario.getIconHeight());
                g.setColor(Color.white);
                g.fillRect(550, HEIGHT - 190 + mario.getIconHeight(), spikes.getIconWidth() + 15, 80);
            }

            mario.paintIcon(null, g, (int) x, (int) y);
        }


        if (puzz1controlTEXT) {
            g.setFont(testfont);
            g.setColor(Color.BLUE);
            g.drawString("OH NO, THE KAPPA ARMY IS HERE!", WIDTH / 2 - 270, HEIGHT / 2);
            for (int i = 0; i < 10; i++) {
                kappa.paintIcon(null, g, 0 + kapWidth * i, 0);
            }
            new newTimer(1);
            if (newTimer.count >= 3) { //TURNS OFF NOTIFICATION AFTER 3 SECONDS
                puzz1control = true;
                puzz1controlTEXT = false;
            }
        }

        if (dead) {
            g.setColor(Color.magenta);
            g.setFont(testfont);
            g.drawString("PRESS ENTER TO EXIT. PRESS SPACE TO RESTART.", 110, 700);
            for (int a = 0; a < 5; a++) {


                for (int i = 0; i < 10; i++) {
                    kappacry.paintIcon(null, g, 100 * i, kappa.getIconHeight() * a);


                }
            }

            grave.paintIcon(null, g, WIDTH / 2 - grave.getIconWidth() / 2 + 20, HEIGHT / 2 - 200);
        }

        if (door1text && door1textb) {
            g.setColor(Color.RED);
            g.setFont(door1font);
            g.drawString("Press space to", doorx, doory - 50);
            g.drawString("advance.", doorx + 17, doory - 40);
        }


        if (puzz1control) {
            for (int i = 0; i < 10; i++) {
                kappa.paintIcon(null, g, kapWidth * i, 0);
            }

            if (biblethumpB)
                biblethump.paintIcon(null, g, (int) (recX[0]), (int) (recY[0]));
            if (brainslugB)
                brainslug.paintIcon(null, g, (int) (recX[1]), (int) (recY[1]));
            if (pjsaltB)
                pjsalt.paintIcon(null, g, (int) (recX[2]), (int) (recY[2]));
            if (kippaB)
                kippa.paintIcon(null, g, (int) (recX[3]), (int) (recY[3]));
            if (kapowB)
                kapow.paintIcon(null, g, (int) (recX[4]), (int) (recY[4]));
            if (bloodtrailB)
                bloodtrail.paintIcon(null, g, (int) (recX[5]), (int) (recY[5]));
            if (lirikthumpB)
                lirikthump.paintIcon(null, g, (int) (recX[6]), (int) (recY[6]));


            //CONTROLS HOW MANY LIVES CHARACTER HAS, -1 EVERY TIME CHARACTER HITS OBJECT
            switch (lives) {

                case 0:
                    dead = true;
                    puzz1control = false;
                case 1:
                    life5.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 2:
                    life10.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 3:
                    life15.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 4:
                    life20.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 5:
                    life25.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 6:
                    life30.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
            }


        }

        if (puzz2control) {
            for (int i = 0; i < 4; i++) {
                frankerz.paintIcon(null, g, frankWidth * i, 0);
            }
            spikes.paintIcon(null, g, spikes1x, spikes1y);
            spikes.paintIcon(null, g, spikes2x, HEIGHT - 138);

            //CONTROLS HOW MANY LIVES CHARACTER HAS, -1 EVERY TIME CHARACTER HITS OBJECT
            switch (lives) {
                case 0:
                    dead = true;
                    puzz2control = false;
                case 1:
                    life5.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 2:
                    life10.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 3:
                    life15.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 4:
                    life20.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 5:
                    life25.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
                case 6:
                    life30.paintIcon(null, g, WIDTH - 173, HEIGHT - 90);
                    break;
            }

        }

        if (endscreen) {
            end.paintIcon(null, g, 0, 0);

        }

    }

    //CHECKS MOVEMENT OF THE OBSTACLES IN THE WAY

    static boolean biblethumpMove = false;
    static boolean brainslugMove = false;
    static boolean pjsaltMove = false;
    static boolean kippaMove = false;
    static boolean kapowMove = false;
    static boolean bloodtrailMove = false;
    static boolean lirikthumpMove = false;

    public double rndSpeed(int maxSpeed) {
        Random r = new Random();
        double rnd;
        rnd = r.nextDouble() * maxSpeed;
        return rnd;
    }


    //COLLISION DETECTION FOR THE WHOLE PROGRAM SEPARATED BY BOOLEANS OF ROOMS IN NESTED IF STATEMENTS

    public void actionPerformed(ActionEvent e) {


        int marioy1 = (int) y + (mario.getIconHeight() / 2);
        int marioy2 = (int) y - (mario.getIconHeight() / 2);


        int mariox1 = (int) x - (mario.getIconWidth() / 2);
        int mariox2 = (int) x + (mario.getIconWidth() / 2);

        if (puzz1control) {

            door1text = false;

            if (mariox1 > doorx - door1.getIconWidth() && mariox1 < doorx + door1.getIconWidth()) {
                door1text = true;
            }

            if (recY[0] <= 30 + kappa.getIconHeight())
                biblethumpMove = true;
            if (recY[0] >= HEIGHT)
                biblethumpMove = false;
            if (biblethumpMove)
                recY[0] += rndSpeed(2);
            else {
                recY[0] -= rndSpeed(2);
            }

            if (recY[1] <= 30 + kappa.getIconHeight())
                brainslugMove = true;
            if (recY[1] >= HEIGHT)
                brainslugMove = false;
            if (brainslugMove)
                recY[1] += rndSpeed(2);
            else
                recY[1] -= rndSpeed(2);

            if (recY[2] <= 30 + kappa.getIconHeight())
                pjsaltMove = true;
            if (recY[2] >= HEIGHT)
                pjsaltMove = false;
            if (pjsaltMove)
                recY[2] += rndSpeed(2);
            else
                recY[2] -= rndSpeed(2);

            if (recY[3] <= 30 + kappa.getIconHeight())
                kippaMove = true;
            if (recY[3] >= HEIGHT)
                kippaMove = false;
            if (kippaMove)
                recY[3] += rndSpeed(3);
            else
                recY[3] -= rndSpeed(3);

            if (recY[4] <= 30 + kappa.getIconHeight())
                kapowMove = true;
            if (recY[4] >= HEIGHT)
                kapowMove = false;
            if (kapowMove)
                recY[4] += rndSpeed(3);
            else
                recY[4] -= rndSpeed(3);

            if (recY[5] <= 30 + kappa.getIconHeight())
                bloodtrailMove = true;
            if (recY[5] >= HEIGHT)
                bloodtrailMove = false;
            if (bloodtrailMove)
                recY[5] += rndSpeed(2);
            else
                recY[5] -= rndSpeed(2);

            if (recY[6] <= 30 + kappa.getIconHeight())
                lirikthumpMove = true;
            if (recY[6] >= HEIGHT)
                lirikthumpMove = false;
            if (lirikthumpMove)
                recY[6] += rndSpeed(2);
            else
                recY[6] -= rndSpeed(2);



            if (
                    (marioy1 >= recY[0] && marioy2 <= recY[0] && mariox1 <= recX[0] && mariox2 >= recX[0]) ||
                            (marioy1 >= recY[1] && marioy2 <= recY[1] && mariox1 <= recX[1] && mariox2 >= recX[1]) ||
                            (marioy1 >= recY[2] && marioy2 <= recY[2] && mariox1 <= recX[2] && mariox2 >= recX[2]) ||
                            (marioy1 >= recY[3] && marioy2 <= recY[3] && mariox1 <= recX[3] && mariox2 >= recX[3]) ||
                            (marioy1 >= recY[4] && marioy2 <= recY[4] && mariox1 <= recX[4] && mariox2 >= recX[4]) ||
                            (marioy1 >= recY[5] && marioy2 <= recY[5] && mariox1 <= recX[5] && mariox2 >= recX[5]) ||
                            (marioy1 >= recY[6] && marioy2 <= recY[6] && mariox1 <= recX[6] && mariox2 >= recX[6])) {
                x = 0;
                lives--;


            }
        }


        if (puzz2control) {
            if (mariox2 < whiteRecX2 && y >= whiteRecY1 && mariox2 > whiteRecX1 && mariox1 < whiteRecX2) {
                y += 4;
                velY = 0;

            }

            if (y > HEIGHT) {
                x = doorx + 3;
                y = HEIGHT - 190;

                lives--;

            }

            if (mariox2 > spikes1x1 && mariox1 < spikes1x2 && y + mario.getIconHeight() > spikes1y1 && y < spikes1y2) {
                lives--;
                x = doorx + 3;
                y = HEIGHT - 190;
            }
            if (mariox2 > spikes2x1 && mariox1 < spikes2x2 && y + mario.getIconHeight() > spikes2y1 && y < spikes2y2) {
                lives--;
                x = doorx + 3;
                y = HEIGHT - 190;
            }

        }


        //THESE MAKE SURE THE CHARACTER DOESN'T GO OUTSIDE OF THE MAP AND STAYS ON THE EMULATED GROUND
        if (puzz2control) {
            if (x < 0) {
                puzz2control = false;
                puzz3control = true;
            }
        } else if (!puzz2control)
            if (x < 0) {
                velX = 0;
                x = 0;
            }

        if (puzz3control) {
            if (x > maxX) {
                puzz3control = false;
                endscreen = true;
            }
        } else if (!puzz3control) {
            if (x > maxX) {
                velX = 0;
                x = maxX;
            }
        }


        if (y < 0) {
            velY = 0;
            y = 0;
        }

        if (!puzz2control) {
            if (y > maxY) {
                velY = 0;
                y = maxY;
            }
        }
        if (room1on) {
            if (x > puzz1x1 && x < puzz1x2 && y < puzz1y2 && y > puzz1y1) {
                room1on = false;
                puzz1controlTEXT = true;
                x = 0;
                y = HEIGHT - 190;
                velX = 0;
                velY = 0;

            }
        }

        if (puzz3control) {

            if (x < ladder1x + ladder1.getIconWidth() - mario.getIconWidth() && x > ladder1x) {
                onladder1 = true;
            }
            if (onladder1) {
                if (x > ladder1x + ladder1.getIconWidth() - mario.getIconWidth()) {
                    velX = 0;
                    x = ladder1x + ladder1.getIconWidth() - mario.getIconWidth();
                }
            }


            if (y < 386) {
                velY = 0;
                on2ndlevel = true;
                onladder1 = false;
            }

            if (on2ndlevel) {
                if (x < ladder1x) {
                    velX = 0;
                    x = ladder1x;
                }
                if (x > ladder1x + ladder1.getIconWidth()) {
                    leftLadder = true;
                }
                if (leftLadder) {
                    if (x <= ladder1x + ladder1.getIconWidth() - mario.getIconWidth() && x >= ladder1x) {
                        cantBack = true;
                    }
                }

            }

            if (y > HEIGHT - 190) {
                velY = 0;
                y = HEIGHT - 190;
            }


        }

        x += velX;
        y += velY;

        //JUMPING PHYSICS, AFFECTED BY VARIABLES THAT WERE DECLARED ORIGINALLY
        //VELOCITY AT START OF JUMP, AT MIDDLE OF JUMP, ETC.
        //USES airbourne AND airbourneDown VARIABLES FOR OTHER PURPOSES (IN OTHER METHODS)

        if (puzz2control) {
            if (jump && !airbourneDown) {

                airbourne = true;
                velY = -velJump;
                y += velY * time;
                velY += gravity * time;
            }

            if (jumpMax >= y) {
                velY = velFall;
                jump = false;
                airbourneDown = true;
            }

            if (airbourneDown) {
                if (y >= groundY) {
                    velY = 0;
                    velX = 0;
                    airbourneDown = false;
                    airbourne = false;
                }
            }
        }


        //repaint() IS IMPORTANT BECAUSE IT USES THE 5MS TIMER TO REPEATEDLY DISPLAY IMAGES
        repaint();


    }


    //KEYCODE IS AN INT AND THAT'S WHAT YOU USE FOR CHECKING WHICH KEY IS PRESSED
    static int c;

    //KEYCODE IS INPUT THROUGH KEYBOARD AND CHECKED WITH KEYEVENT TO DETERMINE LOGIC
    public void keyPressed(KeyEvent e) {

        c = e.getKeyCode();

        if (controlallowed) {

            jump = false;
            airbourne = false;

            if (c == KeyEvent.VK_LEFT) {
                if (!airbourneDown && !airbourne && !onladder1) {
                    velX = -vel;
                    velY = 0;
                    mario = new ImageIcon("src/ISU/marioLEFT.png");
                }
            }

            if (c == KeyEvent.VK_RIGHT) {
                if (!airbourneDown && !airbourne && !onladder1) {
                    velX = vel;
                    velY = 0;
                    mario = new ImageIcon("src/ISU/marioRIGHT.png");
                }
            }


            if (c == KeyEvent.VK_DOWN && startroom && menuchoice > 1)
                menuchoice--;
            if (c == KeyEvent.VK_UP && startroom && menuchoice < 2)
                menuchoice++;

            if (room1on) {
                if (c == KeyEvent.VK_DOWN) {
                    velX = 0;
                    velY = vel;
                }
                if (c == KeyEvent.VK_UP) {
                    velX = 0;
                    velY = -vel;
                }
            }

            if (!puzz2control && !puzz1control && !startroom && onladder1) {
                if (c == KeyEvent.VK_DOWN) {
                    velX = 0;
                    velY = vel;

                }
                if (c == KeyEvent.VK_UP) {
                    velX = 0;
                    velY = -vel;

                }

            }


            if (c == KeyEvent.VK_SPACE) {
                if (door1text) {
                    puzz1control = false;
                    door1textb = false;
                    door1text = false;
                    puzz2control = true;

                } else
                    jump = true;

            }

            if (c == KeyEvent.VK_ENTER) {
                if (endscreen || dead) {
                    System.exit(0);
                }
            }
            if (c == KeyEvent.VK_SPACE) {
                if (dead) {
                    lives = 6;
                    x = WIDTH / 2;
                    y = HEIGHT - 155;
                    dead = false;
                    startroom = true;

                }
            }

        }

    }


    //EXTRA METHOD INCASE NEED TO CHECK FOR OTHER KEYBOARD COMMANDS
    public void keyTyped(KeyEvent e) {
    }

    //INSTRUCTIONS FOR WHAT HAPPENS TO VELOCITY OF CHARACTER WHEN
    //USER LETS GO OF THE KEYS
    //CHARACTER STOPS MOVING
    public void keyReleased(KeyEvent e) {

        if (c == KeyEvent.VK_LEFT) {
            if (!airbourneDown && !airbourne && !jump) {
                velX = 0;
                velY = 0;
            }
        }
        if (c == KeyEvent.VK_RIGHT) {
            if (!airbourneDown && !airbourne && !jump) {
                velX = 0;
                velY = 0;
            }
        }

        if (c == KeyEvent.VK_UP) {
            velX = 0;
            velY = 0;
        }
        if (c == KeyEvent.VK_DOWN) {
            velX = 0;
            velY = 0;
        }
        if (c == KeyEvent.VK_SPACE && !airbourne && !airbourneDown && !jump) {
            velX = 0;
            velY = 0;
        }


    }


    //PUTTING THE WHOLE CLASS INSIDE A JFRAME AND EXECUTING IT
    //AFTER SETTING SOME CONSTRUCTORS (title, size)

    static JFrame jf;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        Game g = new Game();
        jf = new JFrame();
        jf.setTitle("JAMAL'S LABORATORY");
        jf.setSize(WIDTH, HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(g);

        jf.setVisible(true);


    }
}


