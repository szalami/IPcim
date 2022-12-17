
package com.mycompany.ipcim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ipcim {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //szokásos billentyűzetről bekérő cucc
        String ipCim;                       // az ipCim változóban tároljuk amit beír a felhasználó
        
        int[] bajtok = new int[4];          // létrehozunk egy tömböt (egész számokkal - int ) amiben majd az ip cím négy bájtja tárolódik
        
        while(true){                        // egy cikluson belül csináljuk meg az adatok bekérését
            System.out.print("Adjon meg egy IPv4 cimet: ");  
            ipCim = br.readLine();          // eltároljuk az ipCim változóban amit beír a user
            
            String[] darabok = ipCim.split("\\.");  // létrehozunk egy tömböt darabok néven amiben az ipCim-ben tárolt stringet a . mentén daraboljuk
                                                         // változó.split(",") azt jelenti hogy az idézőjelek közötti karakter mentén darabolja a szöveget
             if(darabok.length != 4){ continue; }        // ha a darabok tömb elemeinek a száma nem 4 akkor folytatja a ciklust azaz új adatot kér be
             
             boolean jo = true;                        // a jo változóval vizsgáljuk majd hogy sikeres volt-e az adatok tárolása a bajtok tömbbe
            for(int i = 0; i < 4; i++){                // egy ciklussal végigmegyünk a bajtok tömb elemein, az i lesz a tömb indexe
                try{                                   // a try - catch kapcsolat arra való hogy megpróbálunk(try) végrehajtani valamit ez esetben .... 
                    bajtok[i] = Integer.parseInt(darabok[i]); // ...egész számmá alakítani a user által megadott adatokat
                }catch(Exception ex){                   // ha nem sikerül, mert pl. nem számokat adott meg hanem szöveget akkor a elkapjuk (catch) a kivételt és ...
                    jo = false;                         // ... a catch utáni sorok fognak lefutni, ez esetben a jo változó hamis értéket kap...
                    break;                       // ... és kilép a for ciklusból, mert fölösleges tovább futnia ha volt egy roszz érték pl. a bejövő adat 192.a.0.0 ...
                }                                // ... akkor az 'a' utáni értéket felesleges vizsgálni mert ha egy nem jó akkor az egész sem
                if(bajtok[i] < 0 || bajtok[i] > 255){   // ellenőrizzük hogy az ip cím bájtjai 0 és 255 közé esnek
                    jo = false;                     // ha nem ua. mint feljebb
                    break;
                }
            }
            if(!jo){ continue; }                     // ha hiba van akkor visszatérünk a while ciklusba, azaz új adatot kérünk be...             
                                                     // ... a !jo azt jelenti hogy: a jo változónak false értéke van
            break;                                   // ha a fenti feltétel nem fut le azaz: jo = true akkor kilépünk a while ciklusból, ez azt jelenti hogy az ellenőrzött adatok rendben voltak
            
             }
    
        System.out.println("Lehetseges IPv4 cim!"); // mivel kiléptünk a while ciklusból ez fog lefutni

        if(bajtok[0] >= 192 && bajtok[0] <= 223){       // ezek adják magukat :)
            System.out.println("C osztalyu IP cim");
        }
        if(bajtok[0] >= 128 && bajtok[0] <= 191){
            System.out.println("B osztalyu IP cim");
        }
        if(bajtok[0] >= 1 && bajtok[0] <= 127){
            System.out.println("A osztalyu IP cim");
            if(bajtok[0] == 127){
                System.out.println("Localhost");
            }
        }
        

       
    }
}

