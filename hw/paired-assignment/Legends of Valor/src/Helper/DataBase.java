package Helper;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final List<List<String>> armor_list;
    private final List<List<String>> dragon_list;
    private final List<List<String>> exoskeleton_list;
    private final List<List<String>> fire_spell_list;
    private final List<List<String>> ice_spell_list;
    private final List<List<String>> lightning_spell_list;
    private final List<List<String>> paladin_list;
    private final List<List<String>> potion_list;
    private final List<List<String>> sorcerer_list;
    private final List<List<String>> spirit_list;
    private final List<List<String>> warrior_list;
    private final List<List<String>> weapon_list;
    private final List<List<List<String>>> whole_list = new ArrayList<>();

    public DataBase(){
        FileReader fr = new FileReader();
        armor_list = fr.readTable("./data/Armory.txt");
        dragon_list = fr.readTable("./data/Dragons.txt");
        exoskeleton_list = fr.readTable("./data/Exoskeletons.txt");
        fire_spell_list = fr.readTable("./data/FireSpells.txt");
        ice_spell_list = fr.readTable("./data/IceSpells.txt");
        lightning_spell_list =fr.readTable("./data/LightningSpells.txt");
        paladin_list = fr.readTable("./data/Paladins.txt");
        potion_list = fr.readTable("./data/Potions.txt");
        sorcerer_list = fr.readTable("./data/Sorcerers.txt");
        spirit_list = fr.readTable("./data/Spirits.txt");
        warrior_list = fr.readTable("./data/Warriors.txt");
        weapon_list = fr.readTable("./data/Weaponry.txt");
        whole_list.add(armor_list);
        whole_list.add(dragon_list);
        whole_list.add(exoskeleton_list);
        whole_list.add(fire_spell_list);
        whole_list.add(ice_spell_list);
        whole_list.add(lightning_spell_list);
        whole_list.add(paladin_list);
        whole_list.add(potion_list);
        whole_list.add(sorcerer_list);
        whole_list.add(spirit_list);
        whole_list.add(warrior_list);
        whole_list.add(weapon_list);
    }

    public List<List<String>> getArmor_list() {
        return armor_list;
    }

    public List<List<String>> getDragon_list() {
        return dragon_list;
    }

    public List<List<String>> getExoskeleton_list() {
        return exoskeleton_list;
    }

    public List<List<String>> getFire_spell_list() {
        return fire_spell_list;
    }

    public List<List<String>> getIce_spell_list() {
        return ice_spell_list;
    }

    public List<List<String>> getLightning_spell_list() {
        return lightning_spell_list;
    }

    public List<List<String>> getPaladin_list() {
        return paladin_list;
    }

    public List<List<String>> getPotion_list() {
        return potion_list;
    }

    public List<List<String>> getSorcerer_list() {
        return sorcerer_list;
    }

    public List<List<String>> getSpirit_list() {
        return spirit_list;
    }

    public List<List<String>> getWarrior_list() {
        return warrior_list;
    }

    public List<List<String>> getWeapon_list() {
        return weapon_list;
    }

    public void print_armor_list(){
        Tabulator t = new Tabulator(armor_list);
        t.printTable(3);
    }

    public void print_dragon_list(){
        Tabulator t = new Tabulator(dragon_list);
        t.printTable(3);
    }

    public void print_exoskeleton_list(){
        Tabulator t = new Tabulator(exoskeleton_list);
        t.printTable(3);
    }

    public void print_fire_spell_list(){
        Tabulator t = new Tabulator(fire_spell_list);
        t.printTable(3);
    }

    public void print_ice_spell_list(){
        Tabulator t = new Tabulator(ice_spell_list);
        t.printTable(3);
    }

    public void print_lightning_spell_list(){
        Tabulator t = new Tabulator(lightning_spell_list);
        t.printTable(3);
    }

    public void print_paladin_list(){
        Tabulator t = new Tabulator(paladin_list);
        t.printTable(3);
    }

    public void print_potion_list(){
        Tabulator t = new Tabulator(potion_list);
        t.printTable(3);
    }

    public void print_sorcerer_list(){
        Tabulator t = new Tabulator(sorcerer_list);
        t.printTable(3);
    }

    public void print_spirit_list(){
        Tabulator t = new Tabulator(spirit_list);
        t.printTable(3);
    }

    public void print_warrior_list(){
        Tabulator t = new Tabulator(warrior_list);
        t.printTable(3);
    }

    public void print_weapon_list(){
        Tabulator t = new Tabulator(weapon_list);
        t.printTable(3);
    }

    public void print_spell_list(){
        List<List<String>> spell_list = new ArrayList<>(fire_spell_list);
        for(int i = 1; i < ice_spell_list.size(); i++){
            spell_list.add(ice_spell_list.get(i));
        }
        for(int i = 1; i < lightning_spell_list.size(); i++){
            spell_list.add(lightning_spell_list.get(i));
        }
        Tabulator t = new Tabulator(spell_list);
        t.printTable(3);
    }

    public List<List<String>> inquire(String name){
        List<List<String>> information = new ArrayList<>();
        for(List<List<String>> list: whole_list){
            for(int i = 1; i < list.size(); i++){
                List<String> row = list.get(i);
                if(row.get(0).equalsIgnoreCase(name)){
                    information.add(list.get(0));
                    information.add(row);
                    break;
                }
            }
        }
        return information;
    }

    public void printInformation(String name){
        List<List<String>> information = new ArrayList<>();
        for(List<List<String>> list: whole_list){
            for(int i = 1; i < list.size(); i++){
                List<String> row = list.get(i);
                if(row.get(0).equals(name)){
                    information.add(list.get(0));
                    information.add(row);
                    break;
                }
            }
        }
        if(information.isEmpty()){
            System.out.println("\033[0;31m" + "Invalid input!");
        } else {
            Tabulator t = new Tabulator(information);
            t.printTable(3);
        }
    }

    public void printHeroList(){
        List<List<String>> heroList = new ArrayList<>(sorcerer_list);
        for(int i = 1; i < warrior_list.size(); i++){
            heroList.add(warrior_list.get(i));
        }
        for(int i = 1; i < paladin_list.size(); i++){
            heroList.add(paladin_list.get(i));
        }
        Tabulator t = new Tabulator(heroList);
        t.printTable(3);
    }

    public List<List<String>> getMonsters(int level){
        List<List<String>> monsters = new ArrayList<>();
        for(int i = 1; i < dragon_list.size(); i++){
            List<String> monster = dragon_list.get(i);
            if(Integer.parseInt(monster.get(1)) == level){
                monsters.add(monster);
            }
        }
        for(int i = 1; i < exoskeleton_list.size(); i++){
            List<String> monster = exoskeleton_list.get(i);
            if(Integer.parseInt(monster.get(1)) == level){
                monsters.add(monster);
            }
        }
        for(int i = 1; i < spirit_list.size(); i++){
            List<String> monster = spirit_list.get(i);
            if(Integer.parseInt(monster.get(1)) == level){
                monsters.add(monster);
            }
        }
        return monsters;
    }
}
