package com.finderbar.hilsa.core.datastore

import com.finderbar.hilsa.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _selectedCompanyId = MutableStateFlow<String?>(null)
    val selectedCompanyId: StateFlow<String?> = _selectedCompanyId

    private val _selectedBranchId = MutableStateFlow<String?>(null)
    val selectedBranchId: StateFlow<String?> = _selectedBranchId

    private val _selectedDepartmentId = MutableStateFlow<String?>(null)
    val selectedDepartmentId: StateFlow<String?> = _selectedDepartmentId

    fun setUser(user: User?) {
        _currentUser.value = user
    }

    fun setCompany(id: String) {
        _selectedCompanyId.value = id
    }

    fun setBranch(id: String) {
        _selectedBranchId.value = id
    }

    fun setDepartment(id: String) {
        _selectedDepartmentId.value = id
    }

    fun clear() {
        _currentUser.value = null
        _selectedCompanyId.value = null
        _selectedBranchId.value = null
        _selectedDepartmentId.value = null
    }
}
