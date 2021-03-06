import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.BorderFactory;
import java.awt.geom.Ellipse2D;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Timer;


public class HockeyGUI extends JPanel implements ActionListener
{
 //Buttons and LAbels
 private JLabel lblTeamOne, lblTeamTwo, lblTiming;
 private HockeyCanvas canvas;
 private JButton btnStartGame, btnCreateTeam, btnReset;
 private JTextArea txtGameInfo;

 private PlayerDirectory playerDirectory;
 private boolean teamCreated;
 private HockeyGame game;
 private Timer timer;

 public static void main(String[] args)
 {
  new HockeyGUI();
 }

 public HockeyGUI()
 {
  Font f = new Font("Times Roman", Font.PLAIN, 57);
  JFrame window = new JFrame("Hockey Sim Interface");
  window.setBackground(new Color(255,0,0));
  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  window.setTitle("Hockey Sim Interface");
  window.setSize(614,630);
  window.setLocationRelativeTo(null);
  window.setResizable(false);

  teamCreated=false;
  playerDirectory.init();

  JPanel userInterface = new JPanel();
  userInterface.setLayout(new BorderLayout(20,10));

  JPanel top=new JPanel();
  top.setLayout(new BorderLayout(8,1));

  JPanel buttonBar = new JPanel();
  buttonBar.setLayout(new GridLayout(1,3));
  btnCreateTeam = new JButton("Create Team");
  btnCreateTeam.addActionListener(this);
  buttonBar.add(btnCreateTeam);
  btnStartGame = new JButton("Start Game");
  btnStartGame.setEnabled(false);
  btnStartGame.addActionListener(this);
  buttonBar.add(btnStartGame);
  btnReset = new JButton("Reset");
  btnReset.addActionListener(this);
  buttonBar.add(btnReset);

  JPanel scoreBar = new JPanel();
  lblTeamOne=new JLabel("     COMP  3     ");
  scoreBar.add(lblTeamOne);
  lblTeamTwo=new JLabel("     USER  3     ");
  scoreBar.add(lblTeamTwo);
  lblTiming=new JLabel("Per 1    20:00");
  scoreBar.add(lblTiming);

  top.add(buttonBar, BorderLayout.NORTH);
  top.add(scoreBar, BorderLayout.SOUTH);


  JPanel rinkDisplay = new JPanel();
  rinkDisplay.setLayout(new GridLayout(1,1));
  canvas = new HockeyCanvas();
  rinkDisplay.add(canvas);

  JPanel bottomText = new JPanel();
  txtGameInfo=new JTextArea(8,50);
  txtGameInfo.setEditable(false);
  JScrollPane scrollPane = new JScrollPane(txtGameInfo);
  bottomText.add(scrollPane);

  userInterface.add(rinkDisplay, BorderLayout.CENTER);
  userInterface.add(top, BorderLayout.NORTH);
  userInterface.add(bottomText, BorderLayout.SOUTH);

  window.add(userInterface);
  window.setVisible(true);

  timer = new Timer(5000,this);
 }

 public void actionPerformed(ActionEvent e)
 {
  if(e.getSource()== timer)
  {
   lblTeamOne.setText("     COMP  " + game.getComputerScore() + "     ");
   lblTeamTwo.setText("     USER " + game.getUserScore() + "     ");
   lblTiming.setText(game.getTime());
   game.tick();
   txtGameInfo.setText(game.getPlayByPlay().substring(1));
  }

  if(e.getSource()== btnStartGame&&teamCreated)
  {
   timer.start();
  }

  if(e.getSource()== btnReset)
  {
   lblTeamOne.setText("     COMP  " + game.getComputerScore() + "     ");
   lblTeamTwo.setText("     USER " + game.getUserScore() + "     ");
   timer.stop();
   timer = new Timer(5000,this);
   btnStartGame.setEnabled(false);
  }

  if(e.getSource() == btnCreateTeam)
  {
   JPanel teamCreator = new JPanel();
   teamCreator.setLayout(new GridLayout(3,3));
   teamCreator.add(new JLabel("Center:"));
   JComboBox centerPicker = new JComboBox(PlayerDirectory.fowardList);
   teamCreator.add(centerPicker);
   teamCreator.add(new JLabel("Right Wing:"));
   JComboBox rightWingPicker = new JComboBox(PlayerDirectory.fowardList);
   teamCreator.add(rightWingPicker);
   teamCreator.add(new JLabel("Left Wing:"));
   JComboBox leftWingPicker = new JComboBox(PlayerDirectory.fowardList);
   teamCreator.add(leftWingPicker);
   teamCreator.add(new JLabel("Right Defender:"));
   JComboBox rightDefenderPicker = new JComboBox(PlayerDirectory.defenderList);
   teamCreator.add(rightDefenderPicker);
   teamCreator.add(new JLabel("Left Defender:"));
   JComboBox leftDefenderPicker = new JComboBox(PlayerDirectory.defenderList);
   teamCreator.add(leftDefenderPicker);
   teamCreator.add(new JLabel("Goalie:"));
   JComboBox goaliePicker = new JComboBox(PlayerDirectory.goalieList);
   teamCreator.add(goaliePicker);

   //SwingConstants.CENTER
   int input=JOptionPane.showOptionDialog(null, teamCreator,
    "Create Team", JOptionPane.DEFAULT_OPTION,
     JOptionPane.QUESTION_MESSAGE, null, null, null);

   if(input == JOptionPane.OK_OPTION)
   {
    game=new HockeyGame(new Team((Goalie) goaliePicker.getSelectedItem(), (Forward) rightWingPicker.getSelectedItem(), (Forward) leftWingPicker.getSelectedItem(),
           (Forward) centerPicker.getSelectedItem(), (Defender) rightDefenderPicker.getSelectedItem(), (Defender) leftDefenderPicker.getSelectedItem()));

    lblTeamOne.setText("     COMP  " + game.getComputerScore() + "     ");

    lblTeamTwo.setText("     USER " + game.getUserScore() + "     ");
    teamCreated=true;
    btnStartGame.setEnabled(true);
   }
  }
 }
}