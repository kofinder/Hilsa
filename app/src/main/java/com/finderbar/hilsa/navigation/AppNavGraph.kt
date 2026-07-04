package com.finderbar.hilsa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.finderbar.hilsa.app.MainScreen
import com.finderbar.hilsa.feature.auth.LoginRoute
import com.finderbar.hilsa.feature.auth.login.BranchSelectionScreen
import com.finderbar.hilsa.feature.auth.login.CompanySelectionScreen
import com.finderbar.hilsa.feature.auth.login.DepartmentSelectionScreen
import com.finderbar.hilsa.feature.auth.login.PositionSelectionScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.AUTH_GRAPH
    ) {
        navigation(
            startDestination = Routes.LOGIN,
            route = Routes.AUTH_GRAPH
        ) {
            composable(Routes.LOGIN) {
                LoginRoute(
                    onLoginSuccess = {
                        navController.navigate(Routes.COMPANY_SELECTION)
                    }
                )
            }
            
            composable(Routes.COMPANY_SELECTION) {
                CompanySelectionScreen(
                    onBack = { navController.popBackStack() },
                    onCompanySelected = { company ->
                        navController.navigate("${Routes.BRANCH_SELECTION}/$company")
                    }
                )
            }
            
            composable("${Routes.BRANCH_SELECTION}/{companyName}") { backStackEntry ->
                val companyName = backStackEntry.arguments?.getString("companyName") ?: ""
                BranchSelectionScreen(
                    onBack = { navController.popBackStack() },
                    companyName = companyName,
                    onBranchSelected = { branch ->
                        navController.navigate("${Routes.DEPARTMENT_SELECTION}/$branch")
                    }
                )
            }

            composable("${Routes.DEPARTMENT_SELECTION}/{branchName}") { backStackEntry ->
                val branchName = backStackEntry.arguments?.getString("branchName") ?: ""
                DepartmentSelectionScreen(
                    onBack = { navController.popBackStack() },
                    branchName = branchName,
                    onDepartmentSelected = { dept ->
                        navController.navigate("${Routes.POSITION_SELECTION}/$dept")
                    }
                )
            }

            composable("${Routes.POSITION_SELECTION}/{deptName}") { backStackEntry ->
                val deptName = backStackEntry.arguments?.getString("deptName") ?: ""
                PositionSelectionScreen(
                    onBack = { navController.popBackStack() },
                    departmentName = deptName,
                    onPositionSelected = {
                        navController.navigate(Routes.MAIN_GRAPH) {
                            popUpTo(Routes.AUTH_GRAPH) { inclusive = true }
                        }
                    }
                )
            }
        }

        navigation(
            startDestination = Routes.HOME,
            route = Routes.MAIN_GRAPH
        ) {
            composable(Routes.HOME) {
                MainScreen(
                    onLogout = {
                        navController.navigate(Routes.AUTH_GRAPH) {
                            popUpTo(Routes.MAIN_GRAPH) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
