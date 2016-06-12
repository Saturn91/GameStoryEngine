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