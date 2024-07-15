package org.diary;

import org.diary.service.DiaryService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
        static DiaryService service = new DiaryService();
        static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {

        boolean t = true;

        while (t) {
        System.out.println("Select menu");
        System.out.println("1 : write a diary");
        System.out.println("2 : get a diary");
        System.out.println("3 : get all diaries");
        System.out.println("4 : modify diary");
        System.out.println("5 : remove diary");
        System.out.println("6 : search diary");
        System.out.println("0 : exit");

            switch (sc.nextInt()) {
                case 1:
                    addDiary();
                    break;
                case 2:
                    getOneDiary();
                    break;
                case 3:
                    getAllDiaries();
                    break;
                case 4:
                    modifyDiary();
                    break;
                case 5:
                    removeDiary();
                    break;
                case 6:
                    searchDiaries();
                    break;
                case 0:
                    System.out.println("Exit");
                    t = false;
                    break;
            }
        }
    }

    public static void addDiary(){
        sc.nextLine();
        System.out.println("Set title (under 50)");
        String title = sc.nextLine();
        if (title.length() > 50) {
            System.out.println("title is under 50");
            return;
        }
        System.out.println("Write content (under 500)");
        String content = sc.nextLine();
        if(content.length() > 500){
            System.out.println("content is under 500");
            return;
        }
        service.addDiary(title, content);
    }
    public static void getOneDiary() throws SQLException {
        sc.nextLine();
        System.out.println("Enter diary id");
        int id = sc.nextInt();
        if(id == 0){
            throw new SQLException("Id is not 0");
        }
        service.getOneDiary(id);
    }
    public static void getAllDiaries(){
        service.getAllDiaries();
    }
    public static void removeDiary() throws SQLException {
        sc.nextLine();
        System.out.println("Enter diary id");
        int id = sc.nextInt();
        if(id == 0){
            throw new SQLException("Id is not 0");
        }
        service.removeDiary(id);
    }
    public static void modifyDiary() throws SQLException {
        sc.nextLine();
        System.out.println("Enter diary id");
        int id = sc.nextInt();
        if(id == 0){
            throw new SQLException("Id is not 0");
        }
        sc.nextLine();
        System.out.println("Set title (under 50)");
        String title = sc.nextLine();
        if (title.length() > 50) {
            System.out.println("title is under 50");
            return;
        }
        System.out.println("Write content (under 500)");
        String content = sc.nextLine();
        if(content.length() > 500){
            System.out.println("content is under 500");
            return;
        }

        service.modifyDiary(id, title, content);
    }
    public static void searchDiaries(){
        sc.nextLine();
        System.out.println("Enter keyword");
        String key = sc.nextLine();

        service.searchDiaries(key);
    }
}

