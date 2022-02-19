import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	Scanner scan = new Scanner(System.in);
	
	ArrayList<Data> karyawan = new ArrayList<>();
	
	int man = 0, sup = 0, adm = 0; // Variable untuk menentukan kenaikan gaji 
	
	public Main() {
		menu();
	}
		
	
	private void menu() {
		
		int menu = 0;
		
		System.out.println("<<------PT. Mentol------>>");
		System.out.println("==========================");
		System.out.println("1. Masukkan Data Karyawan");
		System.out.println("2. Lihat Data Karyawan");
		System.out.println("3. Perbarui Data Karyawan");
		System.out.println("4. Hapus Data Karyawan");
		System.out.println("5. Keluar");
		System.out.print("Pilih Menu: ");
		
		try {
			menu = scan.nextInt();
			scan.nextLine();
		} catch (Exception e) {
			System.out.println("Menu Tidak Ditemukan...");
			System.out.println("");
			scan.nextLine();
			menu();
		}
		
		switch (menu) {
		case 1:
			System.out.println("");
			tambahDataKaryawan(0);
			break;
			
		case 2:
			System.out.println("");
			lihatDataKaryawan(0);
			break;
			
		case 3:
			System.out.println("");
			perbaruiDanHapusDataKaryawan(3);
			break;
			
		case 4:
			System.out.println("");
			perbaruiDanHapusDataKaryawan(4);
			break;
		
			
		case 5:
			System.exit(0);
			
		default:
			System.out.println("");
			System.out.println("Menu Tidak Ditemukan...");
			System.out.println("");
			menu();
			break;
		}
		
	}
	
	
	// Menu 1
	private void tambahDataKaryawan(int x) {
		String id = "", nama, gender, jabatan;
		int gaji = 0, random;
		
		// Input ID
		for(int i = 0; i < 2; i++) {
			random = (int)(Math.random()*(90-65+1)+65);
			id += String.valueOf((char) random);
		}
		
		id += "-";
		
		for(int i = 0; i < 4; i++) {
			random = (int)(Math.random()*10);
			id += random;
		}
		
		// Input Nama
		do {
			System.out.print("Masukkan Nama Karyawan [>= 3]: ");
			nama = scan.nextLine();
		} while (nama.length() < 3);
		
		
		//Input Jenis Kelamin
		do {
			System.out.print("Masukkan Jenis Kelamin [Laki-laki / Perempuan] (Case Sensitive): ");
			gender = scan.nextLine();
		} while (!(gender.equals("Laki-laki") || gender.equals("Perempuan")));
		
		
		//Input Jabatan
		do {
			System.out.print("Masukkan Jabatan [Manager / Supervisor / Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
		} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")));
			
		
		//Menentukan gaji dasar
		if(jabatan.equals("Manager")) {
			man++;
			gaji = 8000000;
		} else if(jabatan.equals("Supervisor")) {
			sup++;
			gaji = 6000000;
		} else {
			adm++;
			gaji = 4000000;
		}
		
		
		// Untuk menu 3 dan 4
		if(x != 3) {
			System.out.printf("Berhasil Menambahkan Karyawan dengan ID %s\n", id);
			bonusGaji(jabatan);
			tekanEnter();
			System.out.println();
		} else {
			bonusGaji(jabatan);
			System.out.println("Data Berhasil Diperbarui!");
			System.out.println();
		}
		
		
		karyawan.add(new Data(id, nama, gender, jabatan, gaji));
		
		menu();
	}


	// Menu 2
	private void lihatDataKaryawan(int x) {
		
		int size = karyawan.size();
		int index = 1;
		
		if (size == 0) {
			System.out.println("Data Tidak Ditemukan...");
			System.out.println("");
			menu();
		}
		
		for (int i = 0; i < size-1; i++) {
			for (int j = 0; j < size-1; j++) {
				if (karyawan.get(j).getNama().compareTo(karyawan.get(j+1).getNama()) > 0) {
					Data temp = karyawan.get(j);
					karyawan.set(j, karyawan.get(j+1));
					karyawan.set(j+1, temp);
				}
			}
		}
		
		System.out.println("|----|---------------|--------------------|---------------|-------------|---------------|");
		System.out.println("|No  |Kode Karyawan  |Nama Karyawan       |Jenis Kelamin  |Jabatan      |Gaji Karyawan  |");
		System.out.println("|----|---------------|--------------------|---------------|-------------|---------------|");
		
		for(Data Data : karyawan) {
			System.out.printf("|%4d|%15s|%20s|%15s|%13s|%15d|\n", index, Data.getKode(), Data.getNama(), Data.getGender(), Data.getJabatan(), Data.getGaji());
			index++;
		}
		
		System.out.println("|----|---------------|--------------------|---------------|-------------|---------------|");
		
		if(x != 10) {
			tekanEnter();
			System.out.println("");
			menu();
		}
		
	}
	
	
	// Menu 3 dan 4
	private void perbaruiDanHapusDataKaryawan(int menu) {
		
		int num = 0;
		int size = karyawan.size();
		
		lihatDataKaryawan(10);
		
		System.out.println();
		
		if(menu == 3) {
			System.out.print("Silahkan Masukkan nomor yang Datanya Ingin Diubah: ");
			
			do {
				
				num = 0;
				num = pesanError(size);
				
			} while(num > size);
			
			karyawan.remove(num-1);
			
			tambahDataKaryawan(3);
		}
		
		if(menu == 4) {
			System.out.print("Silahkan Masukkan nomor yang Datanya Ingin Dihapus: ");
			
			do {
				
				num = 0;
				num = pesanError(size);
				
			} while(num > size);
			
			karyawan.remove(num-1);
			
			System.out.println("Data Berhasil Dihapus!");
			System.out.println();
			
			menu();
		}
		
	}
	
	
	// Menentukan bonus gaji
	private void bonusGaji(String s) {
		
		if((man-1) % 3 == 0 && man-1 != 0 && s.equals("Manager")) {
			
			System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan ID");
			
			for(int i = 0; i < karyawan.size(); i++) {
				
				int gaji = karyawan.get(i).getGaji();
				
				if(karyawan.get(i).getJabatan().equals("Manager")) {
					System.out.printf(", %s", karyawan.get(i).getKode());
					karyawan.get(i).setGaji(gaji + gaji*10/100);
				}
			}
			
			System.out.printf("\n");
		} 
		
		
		if((sup-1) % 3 == 0 && sup-1 != 0 && s.equals("Supervisor")) {
			
			System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan ID");
			
			for(int i = 0; i < karyawan.size(); i++) {
					
				int gaji = karyawan.get(i).getGaji();
				
				if(karyawan.get(i).getJabatan().equals("Supervisor")) {
					System.out.printf(", %s", karyawan.get(i).getKode());
					karyawan.get(i).setGaji(gaji + gaji*3/40);
				}
					
			}
			
			System.out.printf("\n");	
		} 
		
		
		if((adm-1) % 3 == 0 && adm-1 != 0 && s.equals("Admin")) {
			
			System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan ID");
			
			for(int i = 0; i < karyawan.size(); i++) {
				
				int gaji = karyawan.get(i).getGaji();
				
				if(karyawan.get(i).getJabatan().equals("Admin")) {
					System.out.printf(", %s", karyawan.get(i).getKode());
					karyawan.get(i).setGaji(gaji + gaji*5/100);
				}
				
			}
			
			System.out.printf("\n");
		}
		
	}

	
	// Pesan error Try catch menu 3 dan 4
	private int pesanError(int size) {
		
		int p = 0;
		
		try {
			p = scan.nextInt();
			scan.nextLine();
		} catch (Exception e) {
			p = 10000;
			scan.nextLine();
		}
		
		if(p > size) {
			System.out.println("Nomor Tidak Ditemukan!");
		}
		
		return p;
	}
	
	
	//Press Enter to continue
	private void tekanEnter() {
		
		System.out.println("Tekan ENTER Untuk Kembali ke Menu...");
		
	    try {
	    	
	    	System.in.read();
	    	
	    } catch(Exception e) {}
	    
	 }
	
	
	public static void main(String[] args) {
		new Main();
	}

}
