package in.ashokit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Plan;
import in.ashokit.service.PlanService;

@RestController
public class PlanRestController {

	@Autowired
	private PlanService planService;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> categories = planService.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String responseMsg = "";
		boolean isSaved = planService.savePlan(plan);
		if (isSaved) {
			responseMsg = "Plan Saved";
		} else {
			responseMsg = "Plan Not Saved";
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans() {
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}") // Added missing slash after "plan"
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}") // Added missing slash after "plan"
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletePlanById(planId);
		String msg = "";
		if (isDeleted) {
			msg = "Plan Deleted"; // Corrected message
		} else {
			msg = "Plan not Deleted"; // Corrected message
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) { // Updated return type to String
		boolean isUpdated = planService.updatePlan(plan);
		String msg = "";
		if (isUpdated) {
			msg = "Plan Updated"; // Corrected message
		} else {
			msg = "Plan not Updated"; // Corrected message
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}") // Fixed method name and parameter types
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		boolean isStatusChange = planService.planStatusChange(planId, status);
		String msg = "";
		if (isStatusChange) {
			msg = "Plan Status Changed"; // Corrected message
		} else {
			msg = "Plan Status not Changed"; // Corrected message
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

}
