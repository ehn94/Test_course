/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem.firstsemexam;

import Entity.Cat;
import Interfaces.Interface;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;

/**
 *
 * @author ehn19
 */
public class Exercises implements Interface {

    private static final String filename = "Cats.txt";
    public static ArrayList<Cat> cats;

    public static void main(String[] args) {
        Exercises eoe = new Exercises();
        cats = eoe.readFile(filename);
        System.out.println(cats.size());
        System.out.println(cats.get(0).getName());
        System.out.println(eoe.getOldest(cats));
        System.out.println("Before remove: ");
        eoe.printCats(cats);
        //eoe.removeSickCats(cats);
        System.out.println("After remove: ");
        eoe.printCats(cats);

        eoe.updateName(cats, "Ib", "Karl");
        System.out.println("After update: ");
        eoe.printCats(cats);

        Cat meow = new Cat("Meow", 12, "Brown", false);

        eoe.updateCat(cats, "Whiskers", meow);
        System.out.println("After second update: ");
        eoe.printCats(cats);
        //String fileContent = eoe.readFile(filename);
        //ArrayList<Cat> cats = eoe.getCats(fileContent);

        System.out.println("Get cats with letter: ");
        System.out.println(eoe.getSomeCats(cats, "B"));
        Cat purr = new Cat("Purr", 1, "Spotted", true);
        //eoe.appendCatToFile(filename, purr);
        //eoe.printCats(cats);
        System.out.println(eoe.sortedCats(cats));
        System.out.println(eoe.getKittens(cats).toString());
    }

    @Override
    public ArrayList<Cat> readFile(String fileName) {
        FileReader TheFileReader;
        BufferedReader TheBufferedReader;
        String newLine;

        ArrayList<Cat> catsArr = new ArrayList();

        try {
            TheFileReader = new FileReader(new File(fileName));
            TheBufferedReader = new BufferedReader(TheFileReader);

            while ((newLine = TheBufferedReader.readLine()) != null) {
                String[] catArr = newLine.split(",");
                catsArr.add(new Cat(catArr[0], Integer.parseInt(catArr[1]), catArr[2], Boolean.parseBoolean(catArr[3])));
            }
            TheBufferedReader.close();
            return catsArr;
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file!");
            System.out.println(ex.toString());
            return catsArr;
        } catch (IOException ex) {
            System.out.println("Could not read from file!");
            System.out.println(ex.toString());
            return catsArr;
        }
    }

    public ArrayList<Integer> readData(String fileName) {
        FileReader TheFileReader;
        BufferedReader TheBufferedReader;
        String newLine;

        ArrayList<Integer> data = new ArrayList();

        try {
            TheFileReader = new FileReader(new File(fileName));
            TheBufferedReader = new BufferedReader(TheFileReader);

            while ((newLine = TheBufferedReader.readLine()) != null) {
                String[] strNumbers = newLine.split(",");
                for (String strNumber : strNumbers) {
                    data.add(Integer.parseInt(strNumber));
                }
                TheBufferedReader.close();
                
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file!");
            System.out.println(ex.toString());
            return data;
        } catch (IOException ex) {
            System.out.println("Could not read from file!");
            System.out.println(ex.toString());
            return data;
        }
        return data;
    }

    @Override
    public void appendCatToFile(String fileName, Cat cat) {

        try (FileWriter fw = new FileWriter(filename, true);
                BufferedWriter bw = new BufferedWriter(fw);) {
            bw.newLine();
            bw.append("\n" + cat.getName() + "," + cat.getAge() + "," + cat.getColor() + "," + cat.getIsSick());

        } catch (IOException ex) {
            ex.toString();
        }
    }

    @Override
    public String getOldest(ArrayList<Cat> cats) {
        int max = 0;
        Cat oldest = null;
        for (int i = 0; i < cats.size(); i++) {
            if (cats.get(i).getAge() > max) {
                max = cats.get(i).getAge();
                oldest = cats.get(i);
            }
        }
        return oldest.getName();
    }

    @Override
    public void removeSickCats(ArrayList<Cat> cats) {
        for (int i = 0; i < cats.size(); i++) {
            if (cats.get(i).getIsSick()) {
                cats.remove(i);
                i--;
            }
        }
    }

    @Override
    public void updateName(ArrayList<Cat> cats, String name, String newName) {

        for (int i = 0; i < cats.size(); i++) {
            if (cats.get(i).getName().equals(name)) {
                cats.get(i).setName(newName);

            }
        }

    }

    @Override
    public void updateCat(ArrayList<Cat> cats, String oldCat, Cat newCat) {
        try {
            for (int i = 0; i < cats.size(); i++) {
                if (oldCat.equals(cats.get(i).getName())) {
                    cats.set(i, newCat);
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void printCats(ArrayList<Cat> cats) {
        System.out.println(cats.toString());
    }

    @Override
    public ArrayList<String> getSomeCats(ArrayList<Cat> cats, String firstLetter) {

        ArrayList<String> subList = new ArrayList();

        cats.stream().filter((c) -> (c.getName().startsWith(firstLetter))).forEach((c) -> {
            subList.add(c.getName());
        });
        return subList;
    }

    @Override
    public ArrayList<String> sortedCats(ArrayList<Cat> cats) {
        ArrayList<String> sortedList = new ArrayList();
        cats.stream().forEach((c) -> {
            sortedList.add(c.getName());
        });
        sortedList.sort(String::compareToIgnoreCase);
        return sortedList;
    }

    @Override
    public ArrayList<Cat> getKittens(ArrayList<Cat> cats) {
        ArrayList<Cat> kittens = new ArrayList();
        cats.stream().filter((c) -> (c.getAge() <= 3)).forEach((c) -> {
            kittens.add(c);
        });
        return kittens;
    }
}
