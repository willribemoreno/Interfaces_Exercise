package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService paymentService;

	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void processContract(Contract contract, int months) {
		
		double baseValue = contract.getContractValue() / months;
		for (int i=1; i<=months; i++) {
			Date dueDate = addMonths(contract.getContractDate(), i);
			Double valueAfterInterest = baseValue + (paymentService.interest(baseValue) * i);
			Double finalValue = valueAfterInterest + paymentService.paymentFee(valueAfterInterest);
			contract.addInstallment(new Installment(dueDate, finalValue));
		}
		
	}

	private Date addMonths(Date date, int increment) {
			
			Calendar aux = Calendar.getInstance();
			aux.setTime(date);
			aux.add(Calendar.MONTH, increment);			

			return aux.getTime();
		}
}
