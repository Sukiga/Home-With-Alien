package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.alien.Alien;
import model.alien.Appearance;
import model.petfood.Inventory;
import model.petfood.PetHomes;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.alien.Personality;

public class MainGUI {
    // actual fields
    private PetHomes petHome; // a pet home
    private Inventory inventory; // an inventory
    private Alien newPet; // a new pet from the user
    private Alien newPet2; // another new pet from the user

    private static final String JSON_STORE = "data/petHomeGraphics.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    
    // fields for Main GUI
    JFrame jframe;
    private JLabel messageLabel;

    BackgroundPanel backgroundPanel = new BackgroundPanel();
    JPanel startButtonPanel;
    Color startButtonColor;
    JButton startButton;
    JPanel quitButtonPanel;
    Color quitButtonColor = new Color(89, 146, 222);
    JButton quitButton;
    Font labelFont = new Font("Times New Roman", Font.BOLD, 12);

    // fields for Home Entry
    PetHomeScreenHandler phsHandler = new PetHomeScreenHandler();
    MainEntryPanel petHomeEntryPanel = new MainEntryPanel();
    CloseListener close = new CloseListener();
    JButton newPetHomeEntryButton;
    JPanel newPetHomeEntryPanel;

    SavingHandler savingH = new SavingHandler();
    LoadingHandler loadingH = new LoadingHandler();
    JPanel mainButtonPanel;
    JButton mainButton;
    JButton saveButton;
    JButton loadButton;
    JButton entryQuitButton;
    JPanel saveButtonPanel;
    JPanel loadButtonPanel;
    JPanel entryQuitButtonPanel;

    // fields for Background Story page
    BackGroundStoryScreenHandler bgssHandler = new BackGroundStoryScreenHandler();
    JPanel backgroundStoryPanel = new JPanel();
    JTextArea backgroundStoryArea;

    JButton storyContinueButton;
    JPanel storyContinuePanel;

    // fields for Pet Home
    PetHomeMainScreenHandler phmsHandler = new PetHomeMainScreenHandler();
    PetHomePanel petHomeMainPanel = new PetHomePanel();

    JButton snowButton;
    JPanel snowButtonPanel;
    JButton newPetButton;
    JPanel newPetButtonPanel;
    JButton newPetTwoButton;
    JPanel newPetTwoButtonPanel;

    JButton inventoryButton;
    JPanel inventoryButtonPanel;


    // fields for Cabin/ Inventory
    InventoryInsideScreenHandler iisHandler = new InventoryInsideScreenHandler();
    InventoryInsidePanel inventoryInsidePanel = new InventoryInsidePanel(); 

    JPanel accessoryButtonPanel;
    JButton accessoryButton;
    JPanel feedButtonPanel;
    JButton feedButton;

    JPanel actionSnowButtonPanel;
    JButton actionSnowButton;
    JPanel actionNewPetButtonPanel;
    JButton actionNewPetButton;
    JPanel actionNewPetTwoButtonPanel;
    JButton actionNewPetTwoButton;

    // fields for accessorizing a pet page
    AccessoryScreenHandler asHandler = new AccessoryScreenHandler();
    JLayeredPane accessorizePanel = new JLayeredPane();

    JButton actionBlackButton;
    JPanel actionBlackButtonPanel;
    JButton actionWhiteButton;
    JPanel actionWhiteButtonPanel;
    JButton actionGoldButton;
    JPanel actionGoldButtonPanel;
    JButton actionPlainButton;
    JPanel actionPlainButtonPanel;
    JButton actionSilverButton;
    JPanel actionSilverButtonPanel;


    ActionAppearanceChoiceListener aacListener;

    // fields for feeding a pet page
    FeedingScreenHandler fsHandler = new FeedingScreenHandler();
    JLayeredPane feedingPanel = new JLayeredPane();

    JButton actionFeedButton;
    JPanel actionFeedButtonPanel;

    ActionFeedingClickerListener afcListener = new ActionFeedingClickerListener();

    ActionFeedingSelectedPet afsPet = new ActionFeedingSelectedPet();

    Alien selectedPet;

    // fields for Snow Page
    SnowScreenHandler ssHandler = new SnowScreenHandler();
    JLayeredPane snowPanel = new JLayeredPane();
    JPanel petBackgroundPanel;

    JTextArea petNameArea;
    JTextArea petLivingStatusArea;
    JTextArea petAppearanceArea;
    JTextArea petAccessoryArea;
    JTextArea petAgeArea;
    JTextArea petIdentityArea;
    JTextArea petPersonalityArea;
    JTextArea petStoryArea;
    JTextArea petStomachStatusArea;

    // fields for new Pet page
    NewPetScreenHandler npsHandler = new NewPetScreenHandler();
    JLayeredPane newPetPanel = new JLayeredPane();

    // fields for second new pet page
    NewPetTwoScreenHandler nptsHandler = new NewPetTwoScreenHandler();
    JLayeredPane newPetTwoPanel = new JLayeredPane();

    // fields for making a new pet page
    MakeNewPetScreenHandler mnpHandler = new MakeNewPetScreenHandler();
    JLayeredPane makeNewPetPanel = new JLayeredPane();
    JTextArea nameArea;
    JTextField nameField = new JTextField();
    JTextArea age;
    JFormattedTextField integerField = new JFormattedTextField();
    JTextArea idArea;
    JTextField idField = new JTextField();
    JTextArea storyArea;
    JTextField storyField = new JTextField(20);
    JButton snowTypeButton;
    JButton petTypeOneButton;
    JButton petTypeTwoButton;
    JButton kindButton;
    JButton shyButton;
    JButton impulsiveButton;
    JButton submitButton;

    Appearance selectedAppearanceType;
    Personality selectedPersonalityType;

    AppearanceChoiceListener acListener = new AppearanceChoiceListener();
    SubmitButtonHandler sbHandler = new SubmitButtonHandler();


    // EFFECTS: petHome is set to petHome and inventory is set to inventory
    //          frame is made and a background Panel is added for the backgroundpicture
    public MainGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        showOnlyPanel(backgroundPanel);
        jframe = new JFrame();
        jframe.setSize(640,360);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundPanel.setLayout(null);
        startQuitButtonSetter();

        
        jframe.add(backgroundPanel);
        

        startButtonPanel.add(startButton, BorderLayout.CENTER);
        quitButtonPanel.add(quitButton, BorderLayout.CENTER);
        backgroundPanel.add(startButtonPanel);
        backgroundPanel.add(quitButtonPanel);
        
        
        jframe.setVisible(true);

        this.petHome = new PetHomes();
        this.inventory = new Inventory();
        Alien snow = new Alien("Snow", 10, "001", "Snow was found near the inventory.");
        snow.setAppearance(Appearance.SNOW);
        snow.setPersonality(Personality.IMPULSIVE);
        petHome.addPets(snow);
    }


    // MODIFIES: startButtonPanel, startButton, startButtonColor
    // EFFECTS: set all the desired visualization for startButton-related variables.
    // MODIFIES: quitButtonPanel, quitButton, quitButtonColor
    // EFFECTS: set all the desired visualization for quitButton-related variables.
    public void startQuitButtonSetter() {
        startButtonPanel = new JPanel(new BorderLayout());
        startButtonPanel.setBounds(285, 190, 70, 30);
        startButton = new JButton("START");
        startButtonColor = new Color(71, 205, 197);
        startButtonPanel.setBackground(startButtonColor);
        startButton.addActionListener(phsHandler);

        quitButtonPanel = new JPanel(new BorderLayout());
        quitButtonPanel.setBounds(285, 250, 70,30);
        quitButtonPanel.setBackground(quitButtonColor);
        quitButton = new JButton("QUIT");
        quitButton.addActionListener(close);
    }

    // methods for the Entry Screen

    // MODIFIES: petHomeEntryPanel, saveButtonPanel, loadButtonPanel, entryQuitButtonPanel
    // EFFECTS: set all the desired visualization for main button panels variables.
    public void createPetHomeEntryScreen() {
        showOnlyPanel(petHomeEntryPanel);
        
        petHomeEntryPanel.setLayout(null);
        jframe.add(petHomeEntryPanel);
        

        newPetHomeEntryButtonSetter();

        newPetHomeEntryPanel.add(newPetHomeEntryButton, BorderLayout.CENTER);
        saveButtonPanel.add(saveButton, BorderLayout.CENTER);
        loadButtonPanel.add(loadButton, BorderLayout.CENTER);
        entryQuitButtonPanel.add(entryQuitButton, BorderLayout.CENTER);
        petHomeEntryPanel.add(newPetHomeEntryPanel);
        petHomeEntryPanel.add(saveButtonPanel);
        petHomeEntryPanel.add(loadButtonPanel);
        petHomeEntryPanel.add(entryQuitButtonPanel);
    }

    // MODIFIES: newPetHomeEntryButton, saveButton, loadButton, entryQuitButton
    // EFFECTS: set all the desired visualization for mainButton variables.
    public void newPetHomeEntryButtonSetter() {
        newPetHomeEntryButton = new JButton();
        newPetHomeEntryButton.setIcon(new ImageIcon("src/main/ui/Pics/NewPetHomeButton.png"));
        newPetHomeEntryPanel = new JPanel(new BorderLayout());
        newPetHomeEntryPanel.setBounds(170,40,300, 160);
        newPetHomeEntryButton.addActionListener(bgssHandler);

        saveButtonPanel = new JPanel(new BorderLayout());
        saveButtonPanel.setBounds(170, 250, 70,30);
        saveButtonPanel.setBackground(new Color(134, 207, 135));
        saveButton = new JButton("SAVE");
        saveButton.addActionListener(savingH);

        loadButtonPanel = new JPanel(new BorderLayout());
        loadButtonPanel.setBounds(285, 250, 70,30);
        loadButtonPanel.setBackground(new Color(231, 127, 59));
        loadButton = new JButton("LOAD");
        loadButton.addActionListener(loadingH);

        entryQuitButtonPanel = new JPanel(new BorderLayout());
        entryQuitButtonPanel.setBounds(400, 250, 70,30);
        entryQuitButtonPanel.setBackground(quitButtonColor);
        entryQuitButton = new JButton("QUIT");
        entryQuitButton.addActionListener(close);
    }

    // methods for Background Story Page

    public void createBackgroundStoryScreen() {
        showOnlyPanel(backgroundStoryPanel);

        backgroundStoryPanel.setBounds(120,40,300, 160);
        backgroundStoryPanel.setLayout(null);
        backgroundStoryPanel.setBackground(Color.BLACK);
        jframe.add(backgroundStoryPanel);
        

        backgroundStoryButtonSetter();
        storyContinuePanel.add(storyContinueButton, BorderLayout.CENTER);
    
        backgroundStoryArea = new JTextArea("\nWelcome to Home with Aliens!" 
        + "\nAs the Valedictorian of UBC 2027, \nYou have been selected to represent Canada\n to live on Mars!!!"
        + "\nYou are now exploring your home on Mars."
        + "\nExplore Your Home!\n");
        backgroundStoryArea.setBounds(100,40,440,160);
        backgroundStoryArea.setLineWrap(true);
        backgroundStoryArea.setBackground(Color.black);
        backgroundStoryArea.setForeground(Color.white);
        backgroundStoryArea.setFont(new Font("Roboto", Font.BOLD, 16));
        backgroundStoryArea.setEditable(false);
        backgroundStoryArea.setFocusable(false);
        backgroundStoryPanel.add(storyContinuePanel);
        backgroundStoryPanel.add(backgroundStoryArea);    
        
    }

    public void backgroundStoryButtonSetter() {
        storyContinueButton = new JButton("CONTINUE");
        storyContinuePanel = new JPanel(new BorderLayout());
        storyContinuePanel.setBackground(Color.WHITE);
        storyContinuePanel.setBounds(270, 250, 100,30);
        storyContinueButton.addActionListener(phmsHandler);
    }

    // methods for Pet Home Main Screen
    public void createPetHomeMainScreen() {
        showOnlyPanel(petHomeMainPanel);
        
        petHomeMainPanel.setLayout(null);
        jframe.add(petHomeMainPanel);
        
        petHomeButtonSetter();
        mainButtonSetter();

        inventoryButtonPanel.add(inventoryButton, BorderLayout.CENTER);
        snowButtonPanel.add(snowButton, BorderLayout.CENTER);
        newPetButtonPanel.add(newPetButton, BorderLayout.CENTER);
        newPetTwoButtonPanel.add(newPetTwoButton, BorderLayout.CENTER);

        petHomeMainPanel.add(inventoryButtonPanel);
        petHomeMainPanel.add(snowButtonPanel);
        petHomeMainPanel.add(newPetButtonPanel);
        petHomeMainPanel.add(newPetTwoButtonPanel);
        mainButtonPanelsSetter(petHomeMainPanel);
    }

    public void petHomeButtonSetter() {
        snowButton = new JButton();
        snowButton.setIcon(new ImageIcon("src/main/ui/Pics/Snow.png"));
        snowButtonPanel = new JPanel(new BorderLayout());
        snowButtonPanel.setBounds(70,200,70, 70);
        snowButton.addActionListener(ssHandler);

        setupNewPetButton();
        setupNewPetTwoButton();

        inventoryButton = new JButton();
        inventoryButton.setIcon(new ImageIcon("src/main/ui/Pics/Inventory.png"));
        inventoryButtonPanel = new JPanel(new BorderLayout());
        inventoryButtonPanel.setBounds(450,200,140, 70);
        inventoryButton.addActionListener(iisHandler);
    }

    public void setupNewPetButton() {
        if (newPetButtonPanel != null) {
            newPetButtonPanel.removeAll();
        } else {
            newPetButtonPanel = new JPanel(new BorderLayout());
            newPetButtonPanel.setBounds(187, 200, 70, 70);
        }
    
    
        if (petHome.getNumPets() >= 2) {
            newPetButton = new JButton();
            newPetButton.setIcon(new ImageIcon(newPet.getAppearance().getSmallImagePath()));
            newPetButton.addActionListener(npsHandler);
        } else {
            newPetButton = new JButton("<html><div style='text-align: center;'>RECORD<br>NEW</html>");
            newPetButton.addActionListener(mnpHandler);
        }
    
        newPetButtonPanel.add(newPetButton, BorderLayout.CENTER);

        newPetButtonPanel.revalidate();
        newPetButtonPanel.repaint();
    }

    public void setupNewPetTwoButton() {
        if (newPetTwoButtonPanel != null) {
            newPetTwoButtonPanel.removeAll();
        } else {
            newPetTwoButtonPanel = new JPanel(new BorderLayout());
            newPetTwoButtonPanel.setBounds(300, 200, 70, 70);
        }
    
    
        if (petHome.getNumPets() == 3) {
            newPetTwoButton = new JButton();
            newPetTwoButton.setIcon(new ImageIcon(newPet2.getAppearance().getSmallImagePath()));
            newPetTwoButton.addActionListener(nptsHandler);
        } else {
            newPetTwoButton = new JButton("<html><div style='text-align: center;'>RECORD<br>NEW</html>");
            newPetTwoButton.addActionListener(mnpHandler);
        }
    
        newPetTwoButtonPanel.add(newPetTwoButton, BorderLayout.CENTER);

        newPetTwoButtonPanel.revalidate();
        newPetTwoButtonPanel.repaint();
    }

    public void mainButtonSetter() {
        mainButtonPanel = new JPanel(new BorderLayout());
        mainButtonPanel.setBounds(15, 10, 70,30);
        mainButtonPanel.setBackground(new Color(134, 207, 135));
        mainButton = new JButton("MAIN");
        mainButton.addActionListener(phmsHandler);

        saveButtonPanel = new JPanel(new BorderLayout());
        saveButtonPanel.setBounds(170, 280, 70,30);
        saveButtonPanel.setBackground(new Color(134, 207, 135));
        saveButton = new JButton("SAVE");
        saveButton.addActionListener(savingH);

        loadButtonPanel = new JPanel(new BorderLayout());
        loadButtonPanel.setBounds(285, 280, 70,30);
        loadButtonPanel.setBackground(new Color(231, 127, 59));
        loadButton = new JButton("LOAD");
        loadButton.addActionListener(loadingH);

        entryQuitButtonPanel = new JPanel(new BorderLayout());
        entryQuitButtonPanel.setBounds(400, 280, 70,30);
        entryQuitButtonPanel.setBackground(quitButtonColor);
        entryQuitButton = new JButton("QUIT");
        entryQuitButton.addActionListener(close);    
    }

    public void mainButtonPanelsSetter(JLayeredPane pane) {
        mainButtonPanel.add(mainButton, BorderLayout.CENTER);
        saveButtonPanel.add(saveButton, BorderLayout.CENTER);
        loadButtonPanel.add(loadButton, BorderLayout.CENTER);
        entryQuitButtonPanel.add(entryQuitButton, BorderLayout.CENTER);
        pane.add(mainButtonPanel, Integer.valueOf(3));
        pane.add(saveButtonPanel, Integer.valueOf(3));
        pane.add(loadButtonPanel, Integer.valueOf(3));
        pane.add(entryQuitButtonPanel, Integer.valueOf(3));
    }

    public void mainButtonPanelsSetter(JPanel panel) {
        mainButtonPanel.add(mainButton, BorderLayout.CENTER);
        saveButtonPanel.add(saveButton, BorderLayout.CENTER);
        loadButtonPanel.add(loadButton, BorderLayout.CENTER);
        entryQuitButtonPanel.add(entryQuitButton, BorderLayout.CENTER);
        panel.add(mainButtonPanel);
        panel.add(saveButtonPanel);
        panel.add(loadButtonPanel);
        panel.add(entryQuitButtonPanel);
    }

    // Inventory stuff below
    public void createInventoryInsideScreen() {
        showOnlyPanel(inventoryInsidePanel);

        inventoryInsidePanel.setLayout(null);
        jframe.add(inventoryInsidePanel);

        mainButtonSetter();
        inventoryInsideButtonSetter();

        accessoryButtonPanel.add(accessoryButton, BorderLayout.CENTER);
        feedButtonPanel.add(feedButton, BorderLayout.CENTER);
        
        inventoryInsidePanel.add(accessoryButtonPanel);
        inventoryInsidePanel.add(feedButtonPanel);
        mainButtonPanelsSetter(inventoryInsidePanel);

    }

    public void inventoryInsideButtonSetter() {
        accessoryButtonPanel = new JPanel(new BorderLayout());
        accessoryButtonPanel.setBounds(125,45,120,30);
        accessoryButton = new JButton("ACCESSORIZE");
        accessoryButton.addActionListener(asHandler);

        feedButtonPanel = new JPanel(new BorderLayout());
        feedButtonPanel.setBounds(400,45,70,30);
        feedButton = new JButton("FEED");
        feedButton.addActionListener(fsHandler);
    }

    // methods for accessorizing a Pet

    public void createAccessorizeScreen() {
        showOnlyPanel(accessorizePanel);
        cleanPaneSetter(accessorizePanel);

        accessorizePanel.setLayout(null);
        accessorizePanel.setBackground(Color.white);
        petBackgroundPanel = new JPanel();
        petBackgroundPanel.setBounds(0,0,640,360);
        petBackgroundPanel.setBackground(Color.white);
        accessorizePanel.add(petBackgroundPanel, Integer.valueOf(0));
        jframe.add(accessorizePanel);

        ImageIcon backgroundImage = new ImageIcon("src/main/ui/Pics/SoloPetBackground.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0,0,320,360);
        accessorizePanel.add(backgroundLabel, Integer.valueOf(1));

        aacListener = new ActionAppearanceChoiceListener(accessorizePanel);
        actionButtonsSetter(accessorizePanel);
        mainButtonSetter();
        mainButtonPanelsSetter(accessorizePanel);
        actionAccessoriesButtonSetter();

        
    }

    public void actionButtonsSetter(JLayeredPane pane) {
        if (petHome.getNumPets() == 1) {
            actionSnowButton();
            actionSnowButtonPanel.setBounds(440, 60, 80, 35);
            pane.add(actionSnowButtonPanel, Integer.valueOf(2));
        } else if (petHome.getNumPets() == 2) {
            actionSnowButton();
            actionSnowButtonPanel.setBounds(373, 60, 80, 35);
            pane.add(actionSnowButtonPanel, Integer.valueOf(2));
            actionNewPetButton();
            actionNewPetButtonPanel.setBounds(506, 60, 80,35);
            pane.add(actionNewPetButtonPanel, Integer.valueOf(2));
        } else if (petHome.getNumPets() == 3) {
            actionSnowButton();
            actionSnowButtonPanel.setBounds(350, 60, 80, 35);
            pane.add(actionSnowButtonPanel, Integer.valueOf(2));
            actionNewPetButton();
            actionNewPetButtonPanel.setBounds(440, 60, 80, 35);
            pane.add(actionNewPetButtonPanel, Integer.valueOf(2));
            actionNewPetTwoButton();
            actionNewPetTwoButtonPanel.setBounds(530, 60, 80, 35);
            pane.add(actionNewPetTwoButtonPanel, Integer.valueOf(2));
        }
        
    }

    public void cleanPanelSetter(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public void cleanPaneSetter(JLayeredPane pane) {
        pane.removeAll();
        pane.revalidate();
        pane.repaint();
    }

    public void actionSnowButton() {
        actionSnowButtonPanel = new JPanel();
        actionSnowButtonPanel.setBackground(Color.WHITE);
        actionSnowButton = new JButton("Snow");
        actionSnowButtonPanel.add(actionSnowButton, BorderLayout.CENTER);
        actionSnowButton.addActionListener(new AppearanceClickerListener(Appearance.SNOW));
        actionSnowButton.addActionListener(aacListener);
        actionSnowButton.addActionListener(afsPet);
        actionSnowButton.setActionCommand("snow");
    }

    public void actionNewPetButton() {
        actionNewPetButtonPanel = new JPanel();
        actionNewPetButtonPanel.setBackground(Color.WHITE);
        actionNewPetButton = new JButton(newPet.getName());
        actionNewPetButtonPanel.add(actionNewPetButton, BorderLayout.CENTER);
        actionNewPetButton.addActionListener(new AppearanceClickerListener(Appearance.PETTYPEONE));
        actionNewPetButton.addActionListener(aacListener);
        actionNewPetButton.addActionListener(afsPet);
        actionNewPetButton.setActionCommand("one");
    }

    public void actionNewPetTwoButton() {
        actionNewPetTwoButtonPanel = new JPanel();
        actionNewPetTwoButtonPanel.setBackground(Color.WHITE);
        actionNewPetTwoButton = new JButton(newPet2.getName());
        actionNewPetTwoButtonPanel.add(actionNewPetTwoButton, BorderLayout.CENTER);
        actionNewPetTwoButton.addActionListener(new AppearanceClickerListener(Appearance.PETTYPETWO));
        actionNewPetTwoButton.addActionListener(aacListener);
        actionNewPetTwoButton.addActionListener(afsPet);
        actionNewPetTwoButton.setActionCommand("two");
    }

    public void actionAccessoriesButtonSetter() {
        actionAccessoryBlackButtonSetter();
        actionAccessoryWhiteButtonSetter();
        actionAccessorySilverButtonSetter();
        actionAccessoryGoldButtonSetter();
        actionAccessoryPlainButtonSetter();
    }

     
    public void actionAccessoryBlackButtonSetter() {
        actionBlackButtonPanel = new JPanel();
        actionBlackButton = new JButton("BLACK");
        actionBlackButton.setBackground(Color.BLACK);
        actionBlackButton.setOpaque(true);
        actionBlackButton.setForeground(Color.WHITE);
        actionBlackButton.setBorderPainted(false);
        actionBlackButtonPanel.add(actionBlackButton, BorderLayout.CENTER);
        actionBlackButtonPanel.setBackground(Color.WHITE);
        actionBlackButtonPanel.setBounds(350, 100, 80, 35);
        accessorizePanel.add(actionBlackButtonPanel, Integer.valueOf(2));
    }

    public void actionAccessoryWhiteButtonSetter() {
        actionWhiteButtonPanel = new JPanel();
        actionWhiteButton = new JButton("WHITE");
        actionWhiteButtonPanel.add(actionWhiteButton, BorderLayout.CENTER);
        actionWhiteButtonPanel.setBackground(Color.WHITE);
        actionWhiteButton.setBackground(new Color(239,239,239));
        actionWhiteButton.setOpaque(true);
        actionWhiteButton.setBorderPainted(false);
        actionWhiteButtonPanel.setBounds(530, 100, 80, 35);
        accessorizePanel.add(actionWhiteButtonPanel, Integer.valueOf(2));
    }

    public void actionAccessorySilverButtonSetter() {
        actionSilverButtonPanel = new JPanel();
        actionSilverButton = new JButton("SILVER");
        actionSilverButton.setBackground(Color.DARK_GRAY);
        actionSilverButton.setForeground(Color.WHITE);
        actionSilverButton.setOpaque(true);
        actionSilverButton.setBorderPainted(false);
        actionSilverButtonPanel.add(actionSilverButton, BorderLayout.CENTER);
        actionSilverButtonPanel.setBackground(Color.WHITE);
        actionSilverButtonPanel.setBounds(395, 140, 80, 35);
        accessorizePanel.add(actionSilverButtonPanel, Integer.valueOf(2));
    }

    public void actionAccessoryGoldButtonSetter() {
        actionGoldButtonPanel = new JPanel();
        actionGoldButton = new JButton("GOLD");
        actionGoldButton.setBackground(Color.yellow);
        actionGoldButton.setOpaque(true);
        actionGoldButton.setBorderPainted(false);
        actionGoldButtonPanel.add(actionGoldButton, BorderLayout.CENTER);
        actionGoldButtonPanel.setBackground(Color.WHITE);
        actionGoldButtonPanel.setBounds(485, 140, 80, 35);
        accessorizePanel.add(actionGoldButtonPanel, Integer.valueOf(2));
    }

    public void actionAccessoryPlainButtonSetter() {
        actionPlainButtonPanel = new JPanel();
        actionPlainButton = new JButton("PLAIN");
        actionPlainButtonPanel.add(actionPlainButton, BorderLayout.CENTER);
        actionPlainButtonPanel.setBackground(Color.WHITE);
        actionPlainButtonPanel.setBounds(440, 100, 80, 35);
        accessorizePanel.add(actionPlainButtonPanel, Integer.valueOf(2));
    }


    // methods for feeding a pet
    public void createFeedingScreen() {
        showOnlyPanel(feedingPanel);
        cleanPaneSetter(feedingPanel);

        feedingPanel.setLayout(null);
        feedingPanel.setBackground(Color.white);
        petBackgroundPanel = new JPanel();
        petBackgroundPanel.setBounds(0,0,640,360);
        petBackgroundPanel.setBackground(Color.white);
        feedingPanel.add(petBackgroundPanel, Integer.valueOf(0));
        jframe.add(feedingPanel);
        

        ImageIcon backgroundImage = new ImageIcon("src/main/ui/Pics/SoloPetBackground.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0,0,320,360);
        feedingPanel.add(backgroundLabel, Integer.valueOf(1));

        aacListener = new ActionAppearanceChoiceListener(feedingPanel);
        mainButtonSetter();
        mainButtonPanelsSetter(feedingPanel);
        actionButtonsSetter(feedingPanel);
        actionFeedButtonSetter();

    }

    public void actionFeedButtonSetter() {
        actionFeedButton = new JButton();
        actionFeedButtonPanel = new JPanel(new BorderLayout());
        actionFeedButtonPanel.setBounds(390, 120, 180, 120);
        actionFeedButton.setIcon(new ImageIcon("src/main/ui/Pics/Feed Button.png"));
        actionFeedButtonPanel.add(actionFeedButton, BorderLayout.CENTER);
        actionFeedButton.addActionListener(afcListener);
        feedingPanel.add(actionFeedButtonPanel, Integer.valueOf(3));

    }

    // methods for Snow

    public void createSnowScreen() {
        showOnlyPanel(snowPanel);
        cleanPaneSetter(snowPanel);

        snowPanel.setBounds(0,0,640, 360);
        snowPanel.setLayout(null);
        petBackgroundPanel = new JPanel();
        petBackgroundPanel.setBounds(0,0,640,360);
        petBackgroundPanel.setBackground(Color.white);
        snowPanel.add(petBackgroundPanel, Integer.valueOf(0));
        
        
        JLabel backgroundLabel = new JLabel(new ImageIcon("src/main/ui/Pics/SoloPetBackground.png"));
        backgroundLabel.setBounds(0,0,320,360);
        snowPanel.add(backgroundLabel, Integer.valueOf(1));

        JLabel snowLabel = new JLabel(new ImageIcon(petHome.getFirstPet().getAppearance().getBigImagePath()));
        snowLabel.setBounds(0,0,320,360);
        snowPanel.add(snowLabel, Integer.valueOf(2));

        jframe.add(snowPanel);

        showPetNameInfo(0);
        petInformationPanelAdder(snowPanel);
        mainButtonSetter();
        mainButtonPanelsSetter(snowPanel);

    }

    public void showPetNameInfo(int n) {
        String name = petHome.getPets().get(n).getName();
        petNameArea = new JTextArea("NAME: " + name);
        petNameArea.setBounds(330, 10, 100, 20);
        petNameArea.setLineWrap(true);
        petNameArea.setForeground(Color.BLACK);
        petNameArea.setFocusable(false);

        showPetAgeInfo(n);
        showPetIdentityInfo(n);
        showPetLivingStatusInfo(n);
        showPetPersonalityInfo(n);
        showPetAppearanceInfo(n);
        showPetAccessoryInfo(n);
        showPetStomachStatus(n);
        showPetStoryInfo(n);
        
    }

    public void showPetAgeInfo(int n) {
        int age = petHome.getPets().get(n).getAge();
        petAgeArea = new JTextArea("AGE: " + age);
        petAgeArea.setBounds(330, 35, 100, 20);
        petAgeArea.setLineWrap(true);
        petAgeArea.setForeground(Color.BLACK);
        petAgeArea.setFocusable(false);
    }

    public void showPetIdentityInfo(int n) {
        String identity = petHome.getPets().get(n).getIdentity();
        petIdentityArea = new JTextArea("IDENTITY ID: " + identity);
        petIdentityArea.setBounds(330, 60, 120, 20);
        petIdentityArea.setLineWrap(true);
        petIdentityArea.setForeground(Color.BLACK);
        petIdentityArea.setFocusable(false);
    }

    public void showPetLivingStatusInfo(int n) {
        String livingStatus = petHome.getPets().get(n).getLivingStatus();
        petLivingStatusArea = new JTextArea("LIVING STATUS: " + livingStatus);
        petLivingStatusArea.setBounds(330,85, 150, 20);
        petLivingStatusArea.setLineWrap(true);
        petLivingStatusArea.setForeground(Color.BLACK);
        petLivingStatusArea.setFocusable(false);
    }

    public void showPetPersonalityInfo(int n) {
        String personality = petHome.getPets().get(n).getPersonality().getPersonality();
        petPersonalityArea = new JTextArea("PERSONALITY: " + personality);
        petPersonalityArea.setBounds(330, 110, 170, 20);
        petPersonalityArea.setLineWrap(true);
        petPersonalityArea.setForeground(Color.BLACK);
        petPersonalityArea.setFocusable(false);
    }

    public void showPetAppearanceInfo(int n) {
        String appearance = petHome.getPets().get(n).getAppearance().getType();
        petAppearanceArea = new JTextArea("APPEARANCE: " + appearance);
        petAppearanceArea.setBounds(330, 135, 200, 20);
        petAppearanceArea.setLineWrap(true);
        petAppearanceArea.setForeground(Color.BLACK);
        petAppearanceArea.setFocusable(false);
    }

    public void showPetAccessoryInfo(int n) {
        String accessory = petHome.getPets().get(n).getAccessory().getType();
        petAccessoryArea = new JTextArea("ACCESSORY: " + accessory);
        petAccessoryArea.setBounds(330, 160, 170, 20);
        petAccessoryArea.setLineWrap(true);
        petAccessoryArea.setForeground(Color.BLACK);
        petAccessoryArea.setFocusable(false);
    }

    public void showPetStomachStatus(int n) {
        String stomachStatus = petHome.getPets().get(n).getStomachStatus();
        petStomachStatusArea = new JTextArea("STOMACH STATUS: " + stomachStatus);
        petStomachStatusArea.setBounds(330, 185, 250, 20);
        petStomachStatusArea.setLineWrap(true);
        petStomachStatusArea.setForeground(Color.BLACK);
        petStomachStatusArea.setFocusable(false);
    }

    public void showPetStoryInfo(int n) {
        String story = petHome.getPets().get(n).getStory();
        petStoryArea = new JTextArea("BACKGROUND: " + story);
        petStoryArea.setBounds(330, 210, 250, 40);
        petStoryArea.setLineWrap(true);
        petStoryArea.setWrapStyleWord(true);
        petStoryArea.setForeground(Color.BLACK);
        petStoryArea.setFocusable(false);
    }


    public void petInformationPanelAdder(JLayeredPane pane) {
        pane.add(petNameArea, Integer.valueOf(3));
        pane.add(petAgeArea, Integer.valueOf(3)); 
        pane.add(petIdentityArea, Integer.valueOf(3));
        pane.add(petLivingStatusArea, Integer.valueOf(3));
        pane.add(petPersonalityArea, Integer.valueOf(3));
        pane.add(petAppearanceArea, Integer.valueOf(3));
        pane.add(petAccessoryArea, Integer.valueOf(3));
        pane.add(petStomachStatusArea, Integer.valueOf(3));
        pane.add(petStoryArea, Integer.valueOf(3));

    }

    // methods for making a new Pet

    public void createMakeNewPetScreen() {
        showOnlyPanel(makeNewPetPanel);
        cleanPaneSetter(makeNewPetPanel);

        makeNewPetPanel.setBounds(0,0,640, 320);
        makeNewPetPanel.setLayout(null);
        petBackgroundPanel = new JPanel();
        petBackgroundPanel.setBounds(0,0,640,360);
        petBackgroundPanel.setBackground(Color.white);
        makeNewPetPanel.add(petBackgroundPanel, Integer.valueOf(0));
        jframe.add(makeNewPetPanel);

        ImageIcon backgroundImage = new ImageIcon("src/main/ui/Pics/SoloPetBackground.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0,0,320,360);
        makeNewPetPanel.add(backgroundLabel, Integer.valueOf(1));

        makeNewPet();
        mainButtonSetter();
        mainButtonPanelsSetter(makeNewPetPanel);

    }

    public void makeNewPet() {
        nameArea = new JTextArea("NAME:");
        nameArea.setBounds(330, 10, 50, 20);
        nameArea.setLineWrap(true);
        nameArea.setForeground(Color.BLACK);
        nameArea.setFocusable(false);
        makeNewPetPanel.add(nameArea, Integer.valueOf(2));  
        nameField.setBounds(390, 10, 70, 20); 
        makeNewPetPanel.add(nameField, Integer.valueOf(2));
        
        makeNewPetAge();
        makeNewPetID();
        makeNewPetStory();
        makeNewPetType();
        makeNewPetPersonality();

        submitButton = new JButton("SUBMIT");
        submitButton.setBounds(400, 170, 150, 100); 
        submitButton.setFont(new Font("ROBOTO", Font.BOLD, 30));
        submitButton.addActionListener(sbHandler);
        makeNewPetPanel.add(submitButton, Integer.valueOf(2));

    }

    public void makeNewPetAge() {
        age = new JTextArea("AGE:");
        age.setBounds(330, 35, 50, 20);
        age.setLineWrap(true);
        age.setForeground(Color.BLACK);
        age.setFocusable(false);

        integerField.setBounds(390, 35, 70, 20); 
        integerField.setColumns(10); 
        integerField.setValue(1);

        makeNewPetPanel.add(age, Integer.valueOf(2));
        makeNewPetPanel.add(integerField, Integer.valueOf(2));
    
    }

    public void makeNewPetID() {
        idArea = new JTextArea("IDENTITY ID:");
        idArea.setBounds(330, 60, 90, 20);
        idArea.setLineWrap(true);
        idArea.setForeground(Color.BLACK);
        idArea.setFocusable(false);

        idField.setBounds(430, 60, 70, 20); 

        makeNewPetPanel.add(idArea, Integer.valueOf(2));
        makeNewPetPanel.add(idField, Integer.valueOf(2));
    
    }

    public void makeNewPetStory() {

        storyArea = new JTextArea("BACKGROUND STORY:");
        storyArea.setBounds(330, 85, 140, 20);
        storyArea.setLineWrap(true);
        storyArea.setForeground(Color.BLACK);
        storyArea.setFocusable(false);

        storyField.setBounds(480, 85, 90, 20); 
        storyField.setCaretPosition(storyField.getText().length());

        makeNewPetPanel.add(storyArea, Integer.valueOf(2));
        makeNewPetPanel.add(storyField, Integer.valueOf(2));
    
    }

    public void makeNewPetType() {

        snowTypeButton = new JButton("Snow");
        snowTypeButton.setBounds(330, 110, 80, 30);
        makeNewPetPanel.add(snowTypeButton, Integer.valueOf(2));
        snowTypeButton.addActionListener(new AppearanceClickerListener(Appearance.SNOW));
        snowTypeButton.addActionListener(acListener);
        snowTypeButton.setActionCommand("snow");


        petTypeOneButton = new JButton("Green Snow");
        petTypeOneButton.setBounds(420, 110, 100, 30);
        makeNewPetPanel.add(petTypeOneButton, Integer.valueOf(2));
        petTypeOneButton.addActionListener(new AppearanceClickerListener(Appearance.PETTYPEONE));
        petTypeOneButton.addActionListener(acListener);
        petTypeOneButton.setActionCommand("one");

        petTypeTwoButton = new JButton("Furious");
        petTypeTwoButton.setBounds(530, 110, 80, 30);
        makeNewPetPanel.add(petTypeTwoButton, Integer.valueOf(2));
        petTypeTwoButton.addActionListener(new AppearanceClickerListener(Appearance.PETTYPETWO));
        petTypeTwoButton.addActionListener(acListener);
        petTypeTwoButton.setActionCommand("two");


    }

    public void makeNewPetPersonality() {

        kindButton = new JButton("KIND");
        kindButton.setBounds(330, 135, 70, 30);
        makeNewPetPanel.add(kindButton, Integer.valueOf(2));
        kindButton.addActionListener(new PersonalityClickerListener(Personality.KIND));
        

        shyButton = new JButton("SHY");
        shyButton.setBounds(410, 135, 50, 30);
        makeNewPetPanel.add(shyButton, Integer.valueOf(2));
        shyButton.addActionListener(new PersonalityClickerListener(Personality.SHY));

        impulsiveButton = new JButton("IMPULSIVE");
        impulsiveButton.setBounds(470, 135, 110, 30);
        makeNewPetPanel.add(impulsiveButton, Integer.valueOf(2));
        impulsiveButton.addActionListener(new PersonalityClickerListener(Personality.IMPULSIVE));
    }

    // methods for new Pet

    public void createNewPetScreen() {
        showOnlyPanel(newPetPanel);
        cleanPaneSetter(newPetPanel);

        newPetPanel.setBounds(120,40,300, 160);
        newPetPanel.setLayout(null);
        petBackgroundPanel = new JPanel();
        petBackgroundPanel.setBounds(0,0,640,360);
        petBackgroundPanel.setBackground(Color.white);
        newPetPanel.add(petBackgroundPanel, Integer.valueOf(0));
        jframe.add(newPetPanel);

        ImageIcon backgroundImage = new ImageIcon("src/main/ui/Pics/SoloPetBackground.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0,0,320,360);
        newPetPanel.add(backgroundLabel, Integer.valueOf(1));

        ImageIcon newPetImage = new ImageIcon(newPet.getAppearance().getBigImagePath());
        JLabel newPetLabel = new JLabel(newPetImage);
        newPetLabel.setBounds(0,0,320,360);
        newPetPanel.add(newPetLabel, Integer.valueOf(2));

        mainButtonSetter();
        mainButtonPanelsSetter(newPetPanel);
        showPetNameInfo(1);
        petInformationPanelAdder(newPetPanel);

    }

    // methods for new Pet Two

    public void createNewPetTwoScreen() {
        showOnlyPanel(newPetTwoPanel);
        cleanPaneSetter(newPetTwoPanel);

        newPetTwoPanel.setBounds(120,40,300, 160);
        newPetTwoPanel.setLayout(null);
        petBackgroundPanel = new JPanel();
        petBackgroundPanel.setBounds(0,0,640,360);
        petBackgroundPanel.setBackground(Color.white);
        newPetTwoPanel.add(petBackgroundPanel, Integer.valueOf(0));
        jframe.add(newPetTwoPanel);
        

        ImageIcon backgroundImage = new ImageIcon("src/main/ui/Pics/SoloPetBackground.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0,0,320,360);
        newPetTwoPanel.add(backgroundLabel, Integer.valueOf(1));

        ImageIcon newPetTwoImage = new ImageIcon(newPet2.getAppearance().getBigImagePath());
        JLabel newPetTwoLabel = new JLabel(newPetTwoImage);
        newPetTwoLabel.setBounds(0,0,320,360);
        newPetTwoPanel.add(newPetTwoLabel, Integer.valueOf(2));

        mainButtonSetter();
        mainButtonPanelsSetter(newPetTwoPanel);
        showPetNameInfo(2);
        petInformationPanelAdder(newPetTwoPanel);


    }

    // 
    public void showOnlyPanel(JPanel panelToShow) {
        backgroundPanel.setVisible(false);
        petHomeEntryPanel.setVisible(false);
        backgroundStoryPanel.setVisible(false);
        inventoryInsidePanel.setVisible(false);
        petHomeMainPanel.setVisible(false);
        snowPanel.setVisible(false);
        makeNewPetPanel.setVisible(false);
        newPetPanel.setVisible(false);
        newPetTwoPanel.setVisible(false);
        accessorizePanel.setVisible(false);
        feedingPanel.setVisible(false);
        panelToShow.setVisible(true);
    }

    public void showOnlyPanel(JLayeredPane panelToShow) {
        backgroundPanel.setVisible(false);
        petHomeEntryPanel.setVisible(false);
        petHomeMainPanel.setVisible(false);
        backgroundStoryPanel.setVisible(false);
        inventoryInsidePanel.setVisible(false);
        snowPanel.setVisible(false);
        makeNewPetPanel.setVisible(false);
        newPetPanel.setVisible(false);
        newPetTwoPanel.setVisible(false);
        accessorizePanel.setVisible(false);
        feedingPanel.setVisible(false);
        panelToShow.setVisible(true);
    }

    // ActionListener stuff Below

    public class PetHomeScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createPetHomeEntryScreen();
        }
    }

    public class BackGroundStoryScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createBackgroundStoryScreen();
        }
    }

    public class PetHomeMainScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createPetHomeMainScreen();
        }
    }

    public class InventoryInsideScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createInventoryInsideScreen();
        }
    }

    public class AccessoryScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createAccessorizeScreen();
        }
    }

    public class FeedingScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createFeedingScreen();
        }
    }

    public class SnowScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createSnowScreen();
        }
    }

    public class MakeNewPetScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createMakeNewPetScreen();
        }
    }

    private class AppearanceClickerListener implements ActionListener {
        private Appearance appearanceType;

        public AppearanceClickerListener(Appearance appearanceType) {
            this.appearanceType = appearanceType;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedAppearanceType = appearanceType;
        }
    }

    private class AppearanceChoiceListener implements ActionListener {

        private JLabel currentImageLabel;

        public AppearanceChoiceListener() {
            this.currentImageLabel = null;
        }

        public void actionPerformed(ActionEvent event) {

            String yourChoice = event.getActionCommand();

            if (currentImageLabel != null) {
                makeNewPetPanel.remove(currentImageLabel);
            }
    
            ImageIcon newPetImage = null;

            switch (yourChoice) {
                case "snow":
                    newPetImage = new ImageIcon("src/main/ui/Pics/BigSnowPlain.png");
                    break;
                case "one":
                    newPetImage = new ImageIcon("src/main/ui/Pics/BigPetType1Plain.png");
                    break;
                case "two":
                    newPetImage = new ImageIcon("src/main/ui/Pics/BigPetType2Plain.png");
                    break;
            }
    
            if (newPetImage != null) {
                currentImageLabel = new JLabel(newPetImage);
                currentImageLabel.setBounds(0, 0, 320, 360);
                makeNewPetPanel.add(currentImageLabel, Integer.valueOf(2));
                makeNewPetPanel.repaint();
            }
            
        }
    }

    private class ActionAppearanceChoiceListener implements ActionListener {

        private JLabel currentImageLabel;
        private JLayeredPane pane;

        public ActionAppearanceChoiceListener(JLayeredPane pane) {
            this.pane = pane;
            this.currentImageLabel = null;
        }

        public void actionPerformed(ActionEvent event) {

            String yourChoice = event.getActionCommand();

            if (currentImageLabel != null) {
                pane.remove(currentImageLabel);
            }
    
            ImageIcon newPetImage = null;

            switch (yourChoice) {
                case "snow":
                    newPetImage = new ImageIcon("src/main/ui/Pics/BigSnowPlain.png");
                    break;
                case "one":
                    newPetImage = new ImageIcon(petHome.getPets().get(1).getAppearance().getBigImagePath());
                    break;
                case "two":
                    newPetImage = new ImageIcon(petHome.getPets().get(2).getAppearance().getBigImagePath());
                    break;
            }
    
            if (newPetImage != null) {
                currentImageLabel = new JLabel(newPetImage);
                currentImageLabel.setBounds(0, 0, 320, 360);
                pane.add(currentImageLabel, Integer.valueOf(2));
                pane.repaint();
            }
            
        }
    }

    private class ActionFeedingSelectedPet implements ActionListener {

        public ActionFeedingSelectedPet() {
        }

        @Override 
        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();

            switch (yourChoice) {
                case "snow":
                    selectedPet = petHome.getFirstPet();
                    break;
                case "one":
                    selectedPet = newPet;
                    break;
                case "two":
                    selectedPet = newPet2;
                    break;
            }
        }
    }

    private class ActionFeedingClickerListener implements ActionListener {

        public ActionFeedingClickerListener() {
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            selectedPet.feedPet(5);
            System.out.println(selectedPet.getStomach());
            System.out.println(selectedPet.getStomachStatus());
            System.out.println(selectedPet.getName());
            createPetHomeMainScreen();
        }
    }

    private class PersonalityClickerListener implements ActionListener {
        private Personality personalityType;

        public PersonalityClickerListener(Personality personalityType) {
            this.personalityType = personalityType;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedPersonalityType = personalityType;
        }
    }

   

    public class SubmitButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String name = nameField.getText();
            Integer age = (Integer) integerField.getValue();
            String id = idField.getText();
            String story = storyField.getText();
            
            if (petHome.getNumPets() == 1) {
                newPet = new Alien(name, age, id, story);
                newPet.setAppearance(selectedAppearanceType);
                newPet.setPersonality(selectedPersonalityType);
                petHome.addPets(newPet);
            } else if (petHome.getNumPets() == 2) {
                newPet2 = new Alien(name, age, id, story);
                newPet2.setAppearance(selectedAppearanceType);
                newPet2.setPersonality(selectedPersonalityType);
                petHome.addPets(newPet2);
            }

            System.out.println(petHome.getNumPets());
            createPetHomeMainScreen();
            nameField.setText("");
            idField.setText("");
            storyField.setText("");
        }
    }

    public class NewPetScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createNewPetScreen();
        }
    }

    public class NewPetTwoScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createNewPetTwoScreen();
        }
    }

    public class SavingHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            savePetHome();;
        }
    }

    public class LoadingHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            loadPetHome();;
        }
    }

    private class CloseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.exit(0);
        }
    }


    // this
    // EFFECTS: saves the pet home to file
    private void savePetHome() {
        try {
            jsonWriter.open();
            jsonWriter.write(petHome);
            jsonWriter.close();
            System.out.println("Saved current pet home to " + JSON_STORE);
            createPetHomeMainScreen();
            messageLabel.setText("Saved successfully!");
            messageLabel.setVisible(true);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
            createPetHomeMainScreen();
            messageLabel.setText("saving failed...");
            messageLabel.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPetHome() {
        try {
            petHome = jsonReader.read();
            System.out.println("Loaded recent pet home from " + JSON_STORE);
            if (petHome.getNumPets() > 1) {
                newPet = petHome.getPets().get(1);
                if (petHome.getNumPets() > 2) {
                    newPet2 = petHome.getPets().get(2);
                }
            } else {
                newPet = null;
            }
            createPetHomeMainScreen();
            messageLabel.setText("loaded successfully");
            messageLabel.setVisible(true);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            createPetHomeMainScreen();

        }
    }
    
    
}

