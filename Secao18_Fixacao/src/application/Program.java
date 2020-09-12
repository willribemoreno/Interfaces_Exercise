package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
		
		System.out.println("*** ENTER THE CONTRACT DATA ***");
		System.out.print("Account number: ");
		int contractNumber = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date contractDate = sdf.parse(sc.next());
		System.out.print("Contract value: ");
		double contractValue = sc.nextDouble();
		System.out.print("Enter the number of installments: ");
		int months = sc.nextInt();
		System.out.println();
		
		Contract contract = new Contract(contractNumber, contractDate, contractValue);
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, months);
		
//		Finalizar o restante...
		System.out.println("Installments: ");
		for (Installment x: contract.getInstallment()) {
			System.out.println(x);
		}
		
		sc.close();

	}

}
