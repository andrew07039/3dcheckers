import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

import java.util.ArrayList;

class Board extends JFrame
implements GLEventListener, KeyListener
{
private GLCapabilities caps;
private GLCanvas canvas;
private GLU glu;
private GLUT glut;
//
private static final int rgba = 4;
private static final int checkImageWidth = 64;
private static final int checkImageHeight = 64;
// private byte checkImage[][][];
// private byte otherImage[][][];
private ByteBuffer checkImageBuf = //
BufferUtil.newByteBuffer(checkImageWidth * checkImageHeight * rgba);
private int[] texName = new int[2];

public Board()
{
super("Board");

caps = new GLCapabilities();
canvas = new GLCanvas(caps);
canvas.addGLEventListener(this);
canvas.addKeyListener(this);

getContentPane().add(canvas);
}

public void run()
{
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(250, 250);
setLocationRelativeTo(null);
setVisible(true);
canvas.requestFocusInWindow();
}

public static void main(String[] args)
{
new Board().run();
}

public void init(GLAutoDrawable drawable)
{
GL gl = drawable.getGL();
glu = new GLU();

gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
gl.glShadeModel(GL.GL_FLAT);
gl.glEnable(GL.GL_DEPTH_TEST);

makeCheckImages();

gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);

gl.glGenTextures(2, texName, 0);
gl.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER,
    GL.GL_NEAREST);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER,
    GL.GL_NEAREST);
gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, checkImageWidth,
    checkImageHeight, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, checkImageBuf);

gl.glBindTexture(GL.GL_TEXTURE_2D, texName[1]);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER,
    GL.GL_NEAREST);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER,
    GL.GL_NEAREST);
gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
gl.glEnable(GL.GL_TEXTURE_2D);
}

public void display(GLAutoDrawable drawable)
{
GL gl = drawable.getGL();

gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
gl.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);
gl.glBegin(GL.GL_QUADS);
gl.glTexCoord2d(0.0, 0.0);
gl.glVertex3d(-2.0, -1.0, 0.0);
gl.glTexCoord2d(0.0, 1.0);
gl.glVertex3d(-2.0, 1.0, 0.0);
gl.glTexCoord2d(1.0, 1.0);
gl.glVertex3d(0.0, 1.0, 0.0);
gl.glTexCoord2d(1.0, 0.0);
gl.glVertex3d(0.0, -1.0, 0.0);
gl.glEnd();
gl.glFlush();
}

public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
{
GL gl = drawable.getGL();

gl.glViewport(0, 0, w, h);
gl.glMatrixMode(GL.GL_PROJECTION);
gl.glLoadIdentity();
glu.gluPerspective(60.0, (float) w / (float) h, 1.0, 30.0);
gl.glMatrixMode(GL.GL_MODELVIEW);
gl.glLoadIdentity();
gl.glTranslated(0.8, 0.0, -3.5);
}

public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
  boolean deviceChanged)
{
}

private void makeCheckImages()
{
byte c = 0x00;

for (int i = 0; i < checkImageWidth; i++)
{
  for (int j = 0; j < checkImageHeight; j++)
  {

    c = (byte) ((((byte) ((i & 0x8) == 0 ? 0x00 : 0xff)//
    ^ (byte) ((j & 0x8) == 0 ? 0x00 : 0xff))));
    // checkImage[i][j][0] = (byte) c;
    // checkImage[i][j][1] = (byte) c;
    // checkImage[i][j][2] = (byte) c;
    checkImageBuf.put((byte) c);
    checkImageBuf.put((byte) c);
    checkImageBuf.put((byte) c);
    checkImageBuf.put((byte) 0xff);

  }
}
checkImageBuf.rewind();
}

public void keyTyped(KeyEvent key)
{
}

public void keyPressed(KeyEvent key)
{
switch (key.getKeyCode()) {
  case KeyEvent.VK_ESCAPE:
    System.exit(0);
  default:
    break;
}
}

public void keyReleased(KeyEvent key)
{
}
}/*extends JFrame {

	/* GL, display, model transformation, and mouse control variables 
	private final GLCanvas canvas;
	private GL gl;
	int	[]	textures=new int[14];


	private final GLU glu = new GLU();
	Point2D[]	board_table=new Point2D[32];
	float WHITE[] = { 1, 1, 1 };
	float RED[] = { 1, 0, 0 };
	private int winW = 800, winH = 800;

	int displayListId;

	class Checkerboard {
		int width;
		int depth;

		public Checkerboard(int w, int d) {
			width = w;
			depth = d;
		}

		double centerx() {
			return width / 2;
		}

		double centerz() {
			return depth / 2;
		}

		void create() {
			displayListId = gl.glGenLists(1);
			gl.glNewList(displayListId, gl.GL_COMPILE);
			float lightPosition[] = { 4, 3, 7, 1 };
			gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, lightPosition, 0);
			gl.glBegin(gl.GL_QUADS);
			gl.glNormal3d(0, 1, 0);
			for (int x = 0; x < width - 1; x++) {
				for (int z = 0; z < depth - 1; z++) {
					gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT_AND_DIFFUSE,
							(x + z) % 2 == 0 ? RED : WHITE, 0);
					gl.glVertex3d(x, 0, z);
					gl.glVertex3d(x + 1, 0, z);
					gl.glVertex3d(x + 1, 0, z + 1);
					gl.glVertex3d(x, 0, z + 1);
				}
			}
			gl.glEnd();
			gl.glEndList();
		}

		void draw() {
			gl.glCallList(displayListId);
		}
	};

	Checkerboard checkerboard = new Checkerboard(8, 8);

	public void display(GLAutoDrawable drawable) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		checkerboard.draw();
		gl.glFlush();

		/* === YOUR WORK HERE === */

		/*
		 * Below is an example of a rotating bunny It rotates the bunny with
		 * example_rotateT degrees around the bunny's gravity center
		 

	}

	public Board() {
		canvas = new GLCanvas();
		getContentPane().add(canvas);
		setSize(winW, winH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		canvas.requestFocus();
	}

	public static void main(String[] args) {
		new Board();
	}

	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL();
		gl.glEnable(gl.GL_DEPTH_TEST);
		gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, WHITE, 0);
		gl.glLightfv(gl.GL_LIGHT0, gl.GL_SPECULAR, WHITE, 0);
		gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, WHITE, 0);
		gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, 30);
		gl.glEnable(gl.GL_LIGHTING);
		gl.glEnable(gl.GL_LIGHT0);
	}

	void Draw_Square(float x, float y, int top) {
		gl.glRotatef(-90, 1, 0, 0);
		gl.glTranslatef(x, y, 0);

		gl.glEnable(gl.GL_TEXTURE_2D);
		gl.glBindTexture(gl.GL_TEXTURE_2D, top);

		gl.glColor3f(1.0f, 1.0f, 0.0f);
		gl.glBegin(gl.GL_QUADS);
		gl.glTexCoord2f(0, 1);
		gl.glVertex3d(-0.5, -0.5, 0);
		gl.glTexCoord2f(1, 1);
		gl.glVertex3d(0.5, -0.5, 0);
		gl.glTexCoord2f(1, 0);
		gl.glVertex3d(0.5, 0.5, 0);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3d(-0.5, 0.5, 0);
		gl.glEnd();

		gl.glTranslatef(-x, -y, 0);
		gl.glRotatef(90, 1, 0, 0);

		gl.glDisable(gl.GL_TEXTURE_2D);
	}

	void Draw_Empty_Board() {
		int i;

		for (i = 0; i < 32; ++i)

			Draw_Square(-board_table[i].getX(), board_table[i].getY(), textures[13]);

		gl.glRotatef(-90, 1, 0, 0);

		gl.glEnable(gl.GL_TEXTURE_2D);

		gl.glBindTexture(gl.GL_TEXTURE_2D, textures[3]);

		gl.glBegin(gl.GL_QUADS);
		gl.glTexCoord2f(0f, 1.5f);
		gl.glVertex3d(-5.5, -5.5, 0);
		gl.glTexCoord2f(11f, 1.5f);
		gl.glVertex3d(5.5, -5.5, 0);
		gl.glTexCoord2f(11, 0);
		gl.glVertex3d(5.5, -4.0, 0);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3d(-5.5, -4.0, 0);
		gl.glEnd();

		gl.glBegin(gl.GL_QUADS);
		gl.glTexCoord2f(0f, 1.5f);
		gl.glVertex3d(5.5, 5.5, 0);
		gl.glTexCoord2f(11f, 1.5f);
		gl.glVertex3d(-5.5, 5.5, 0);
		gl.glTexCoord2f(11, 0);
		gl.glVertex3d(-5.5, 4.0, 0);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3d(5.5, 4.0, 0);
		gl.glEnd();

		gl.glBegin(gl.GL_QUADS);
		gl.glTexCoord2f(0, 8);
		gl.glVertex3d(-4.0, 4.0, 0);
		gl.glTexCoord2f(1.5f, 8);
		gl.glVertex3d(-5.5, 4.0, 0);
		gl.glTexCoord2f(1.5f, 0);
		gl.glVertex3d(-5.5, -4.0, 0);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3d(-4.0, -4.0, 0);
		gl.glEnd();

		gl.glBegin(gl.GL_QUADS);
		gl.glTexCoord2f(0, 8);
		gl.glVertex3d(4.0, 4.0, 0);
		gl.glTexCoord2f(1.5f, 8);
		gl.glVertex3d(5.5, 4.0, 0);
		gl.glTexCoord2f(1.5f, 0);
		gl.glVertex3d(5.5, -4.0, 0);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3d(4.0, -4.0, 0);
		gl.glEnd();

		gl.glRotatef(90, 1, 0, 0);

		gl.glDisable(gl.GL_TEXTURE_2D);
	}
	void LoadGLtextures()
	{
		
		
	}
}*/
