Names: Zixiong Cheng, Rohit Menon
Email: chouchou@bu.edu, rmenon@bu.edu
BU ID: U38309953, U50262438

Compilation instructions:

    cd src

    javac Start.java

    cd ..

    java -cp src Start



General execution description:

The game starts by asking the user to enter the names of the heroes that will be on their team. Once the team has been assembled, the heroes are placed on their nexus cells on the game map. The game contains three lanes, separated by columns of inaccessible cells, which are slashed out. The accessible cells are distinguished with the following symbols:

* - nexus
& - bush
% - koulou
W - cave


When a hero is on a nexus cell, they will be given the choice of entering the market. On other cells, the following commands are usually available:

w - move north
a - move west
s - move south
d - move east
t - teleport to another lane
b - teleport to original nexus
m - open game menu

When a hero confronts a monster, they will engage in a fight until one of them is defeated. During each turn, they can choose to do either a normal attack or a spell. If the hero faints, they teleport back to their nexus. If the monster faints, the monster is removed from the board.

When a hero is not engages in a fight, he can feel free to make as many actions as possible before he moves to the next cell. These actions include checking inventory, using items, checking hero's stat and reading manual.

The game is over when a hero reaches a monster nexus (in which case the heroes win), or a monster reaches a hero nexus (in which case the monsters win). A tie results in a win for the heroes.




Detailed execution description:

The program's main function is in the Start.java file.

When run, the program shows the welcome screen, and gives a list of the possible heroes to choose from. The user will be allowed to enter the name of a hero, and the program will add that hero to the team. In order to fill the team slots, this will be done three times. (If the user enters an invalid name or a repeated name, the program will alert the user of this and request that another name be entered.) Once the team has been formed, the game begins.

In the game map, the symbols in the corner of each cell represent the cell's type. Other than the plain spaces, which don't use a symbol, there are four types of accessible cells: nexus (*), bush (&), koulou (%), and cave (W). The nexus cells are where the heroes/monsters begin, and also serve as markets. If a hero is on a bush, koulou, or cave cell, they will receive a boost in dexterity, strength, or agility, respectively. Cells that are filled with the / symbol are considered inaccessible. These cells are used to create the boundary between lanes. 

(In the default implementation of the game, the map is 8x8, with the third and sixth columns being completely inaccessible, creating 3 lanes. However, the game code itself allows these values to be changed, as the LegendMap class accepts these settings as parameters in its constructor.)

At the start of the first round, three monsters are spawned, one for each lane (this will occur again every 8 rounds). The heroes begin on their own nexuses in each lane. Because the nexuses double as markets, on the first round (or whenever a hero is on a nexus), the user will be asked if the hero will enter the market before the hero's move. 

When a hero enters a market, they will be allowed to choose which type of item they would like to buy. Once they choose the item type, they will be shown the full list of what can be bought. The user can enter the name of the desired item, and if the user has the required money and level, it will be purchased. After buying an item, the program will ask the user whether or not they want to buy another item, and goes through the process again if they do. If they do not, then the program returns to the game map so that the hero can make a move.

Items purchased in the market will appear in the hero's inventory. To use them, enter the menu using command m and open the inventory using command i. Follow the instructions on screen and enter the name of the item hero wants to use. Also, heroes are allowed to sell their items in their inventory by half of the original price. It's worth mentioning that spells purchased in the market are also stored in the inventory. To use them in a fight, hero needs to learn them in the inventory. A spell can be sold if it's never learned. However, once it's learned, it will be removed from the inventory and added to hero's spell list, which can be used during a fight.

On the game map, a hero can move through the w/a/s/d keys (corresponding to north/east/south/west, respectively). Another command is t, which allows a hero to teleport to a nexus in another lane. Additionally, the b command can be used to return a hero to their original nexus. The user also has the option of using the m command--this pulls up the game's menu. At this screen, the user is given the option to go through the Hero's inventory, show the hero's stats, read a review of the game commands, close the menu, or quit the game.

When a hero comes up against a monster, a fight begins. In this case, on that hero's turn, they must choose to either use a normal attack or a spell. This state continues until the hero or the monster is defeated. If the monster is defeated, the hero will gain experience (and, when enough experience is gained, level up). If the hero is defeated, they will be revived back at their original nexus with 10% of max HP. However, if the original nexus is occupied, they will be revived in the lane next to it.

The game is over when any of the monsters reach a hero nexus (in which case the monsters win), or when any of the heroes reach a monster nexus (in which case the heroes win). If both teams reach the opposing nexus on the same round, it is considered a win for the heroes.





Class descriptions:

The Start class exists solely to provide an entry point for the program.

All other classes are grouped into folders for the sake of organization.

The Game folder includes files related to the general structure and flow of the game. The abstract Game class represents a general game that can be played. It is extended by the abstract RPG class, which specifies that the game is an RPG. The RPG class is in turn extended by the LegendsOfValor class. This class contains most of the logic defining the flow of the program, as it controls what happens in each round.

The UserInterface folder has files that relate to communication with the user. The MyScanner class manages the game's input, as it allows the program to wait for a particular command from the user. The Graphic class retrieves text-based graphics from files so that they can be sent to output. The TextMessageDecorator also helps with the output, as it is simply used to add color to outputted text. Another useful class for communication is the Confirmation class, which lets the program easily ask the user to confirm that they want to do a certain action. The GameIO class is an abstract class that is extended by a variety of classes which communicate with the user. For example, the MarketInterface class and InventoryInterface class extend GameIO in order to allow the user to go through the market and their inventory, respectively. The GameInterface class extends GameIO as well, as it provides a way for the user to input hero names and spells. The Manual class is similar, as it extends GameIO to allow the user to interact with the game's menu. Finally, the Controller class extends GameIO in order to allow the user to input the direction in which the want to move for their turn.

The Helper folder contains classes that help in the creation of the game. One example is the Tabulator class, which is used throughout the program simply to format output as a table. The FileReader class provides a mechanism by which the game's data files are read; this feature is used by the DataBase class, which stores all of the outside data needed for the program.

This folder also contains the Create interface, whose purpose is to provide classes with a "create" method. The presence of this method indicates that a class is able to create instances of some object. For this reason, the Create interface is implemented by the abstract class Factory. HeroFactory, ItemFactory, and MonsterFactory all inherit from this class, as they are used to create items, heroes, and monsters, respectively.

The Player folder contains the Player class, whose primary purpose is to allow for the creation of the player's team. It also contains the HeroTeam class, which holds the group of heroes that make up the player's team.

The Market folder just contains the Market class. The Market class contains an instance of ItemFactory, which it uses to create new items. This functionality is used when a hero requests an item, as the market creates a new item based on the selected name.

The Creatures folder holds information relating to the heroes and monsters in the game. The Creature class is an abstract class that represents any moving individual on the grid. It is extended by two other abstract classes, Hero and Monster. The Hero class represents the general concept of a hero, while the Sorcerer, Paladin, and Warrior classes extend it to represent particular hero types. Similarly, the Monster class represents the general concept of a monster, while its subclasses, Dragon, Exoskeleton, and Spirit, represent particular monster types.

This folder also contains a few other classes related to the individuals on the map. There is a class called NumericAttribute, which is used to hold information about each hero's stats. The Hand class represents a hero's hand--this representation is necessary, as the status of each hand determines the availability of weapons during a fight. For this reason, there is also a Hands class, which manages the status of both of a hero's hands.

Another folder in the project is the Items folder. This folder contains the Item class, which is an abstract class representing the general concept of an item. This class implements the Sellable interface, which indicates objects that can be bought/sold for a particular price. The Item class is extended by the Armor, Potion, Spell, and Weapon classes, which each represent a different type of item. The Spell class is extended by FireSpell, IceSpell, and LightningSpell, as these are specific types of spells that have their own effects.

Another folder is dedicated to the "stuff" that the heroes carry with them. One class in this folder is Inventory, which functions as a container for all of the hero's items. Similarly, the Wallet class contains the money that the hero has. Another class is the Tracker class, which is used by both heroes and monsters to keep track of their location on the map.

The Map folder groups together all files relating to the game's geography. The overall map is represented by the grid defined in the LegendMap class, and consists of cells, which are defined in the Cell class. The GameArea class is an abstract class that indicates what kind of area exists at a cell--its subclasses are PlainCell, Nexus, Bush, Koulou, and Cave. There is also a Lane class--this represents a group of cells that form a "lane" on the grid. All of this is formatted with the help of the MapGraphics class, which uses StringBuilders to assemble a visual version of the map.

In addition, the Map folder holds several factory classes that are used to create this setup. The CellFactory class is an abstract class that represents a general factory with the ability to create cells. It is extended by the RandomCellFactory class, the SequentialCellFactory class, and the abstract FixedCellFactory class. The RandomCellFactory class uses instances of other factories to randomly create cells using given probabilities. The SequentialCellFactory class uses sub-factories as well, but uses them to create cells in a predetermined order. The FixedCellFactory is an abstract class that represents factories that output just one type of cell. Its subclasses are BushCellFactory, KoulouCellFactory, CaveCellFactory, NexusCellFactory, and PlainCellFactory, which each create their particular type of cell. 
