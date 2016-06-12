addCreature: Player 10;
addCreature: Watchman 15;
addCreature: Lady 5;

addThing: Dagger;
addThing: Key;
addThing: Sword;
addThing: Ring;

addEvent: InInventory E1 Player Dagger;
addEvent: InInventory E2 Player Key;
addEvent: DeathOf E3 Watchman;
addEvent: Pos E4 Player 10 10;
addEvent: Read E5 T4 Lady;

addAction: Give Player Sword E3;
addAction: Take Player Sword E4;
addAction: Give Player Ring E5;

addEventBefore: E1 [START];
addEventBefore: E2 [START];
addEventBefore: E3 E1;
addEventBefore: E4 E3;
addEventBefore: E4 E2;
addEventBefore: E5 E4;
