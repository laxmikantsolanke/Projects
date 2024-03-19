package in.ashokit.service;

import java.util.List;
import java.util.Map;

import in.ashokit.entity.Plan;

public interface PlanService {

	public Map<Integer, String> getPlanCategories();// to display drop down data

	public boolean savePlan(Plan plan);// To save the plan and update

	public List<Plan> getAllPlans();// view all plans

	public Plan getPlanById(Integer planId);// get plan by id

	public boolean updatePlan(Plan plan);

	public boolean deletePlanById(Integer planId);

	public boolean planStatusChange(Integer planId, String status);

}
