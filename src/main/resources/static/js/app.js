

var app = angular.module('warrantyWaveApp', []);

app.controller('MainController', ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {

    $scope.currentTab = 'dashboard';
    $scope.statusFilter = 'ALL';
    $scope.searchQuery = '';

    $scope.claims = [];
    $scope.vehicles = [];
    $scope.contracts = [];
    $scope.customers = [];

    $scope.loading = false;
    $scope.toasts = [];

    $scope.modals = {
        claim: false,
        vehicle: false,
        contract: false,
        customer: false
    };

    $scope.mobileMenuOpen = false;
    $scope.toggleMobileMenu = function() {
        $scope.mobileMenuOpen = !$scope.mobileMenuOpen;
    };
    $scope.closeMobileMenu = function() {
        $scope.mobileMenuOpen = false;
    };

    $scope.newClaim = {};
    $scope.newVehicle = {};
    $scope.newContract = {};
    $scope.newCustomer = {};

    $scope.setTab = function(tab) {
        $scope.currentTab = tab;
        $scope.searchQuery = '';
        $scope.mobileMenuOpen = false;
    };

    $scope.showToast = function(message, type) {
        var toast = {
            id: Date.now(),
            message: message,
            type: type || 'success'
        };
        $scope.toasts.push(toast);
        $timeout(function() {
            $scope.toasts = $scope.toasts.filter(function(t) { return t.id !== toast.id; });
        }, 4000);
    };

    $scope.loadClaims = function() {
        return $http.get('/api/claims').then(function(res) {
            $scope.claims = res.data || [];
            $scope.updateMetrics();
        }, function(err) {
            console.error('Error fetching claims', err);
        });
    };

    $scope.loadVehicles = function() {
        return $http.get('/api/vehicles').then(function(res) {
            $scope.vehicles = res.data || [];
        }, function(err) {
            console.error('Error fetching vehicles', err);
        });
    };

    $scope.loadContracts = function() {
        return $http.get('/api/finance-contracts').then(function(res) {
            $scope.contracts = res.data || [];
        }, function(err) {
            console.error('Error fetching contracts', err);
        });
    };

    $scope.loadCustomers = function() {
        return $http.get('/api/customers').then(function(res) {
            $scope.customers = res.data || [];
        }, function(err) {
            console.error('Error fetching customers', err);
        });
    };

    $scope.loadAll = function() {
        $scope.loading = true;
        Promise.all([
            $scope.loadClaims(),
            $scope.loadVehicles(),
            $scope.loadContracts(),
            $scope.loadCustomers()
        ]).finally(function() {
            $scope.$apply(function() {
                $scope.loading = false;
            });
        });
    };

    $scope.metrics = {
        totalClaims: 0,
        approvedClaims: 0,
        submittedClaims: 0,
        rejectedClaims: 0,
        totalClaimAmount: 0
    };

    $scope.updateMetrics = function() {
        var total = $scope.claims.length;
        var approved = 0;
        var submitted = 0;
        var rejected = 0;
        var amount = 0;

        angular.forEach($scope.claims, function(c) {
            var s = (c.status || '').toUpperCase();
            if (s === 'APPROVED') approved++;
            else if (s === 'REJECTED') rejected++;
            else submitted++;

            if (c.claimAmount) amount += Number(c.claimAmount);
        });

        $scope.metrics.totalClaims = total;
        $scope.metrics.approvedClaims = approved;
        $scope.metrics.submittedClaims = submitted;
        $scope.metrics.rejectedClaims = rejected;
        $scope.metrics.totalClaimAmount = amount;
    };

    $scope.openClaimModal = function() {
        $scope.newClaim = {
            claimDate: new Date().toISOString().split('T')[0],
            status: 'Submitted'
        };
        $scope.modals.claim = true;
    };

    $scope.submitClaim = function() {
        $http.post('/api/claims', $scope.newClaim).then(function(res) {
            $scope.showToast('Warranty Claim #' + res.data.id + ' created successfully!', 'success');
            $scope.modals.claim = false;
            $scope.loadClaims();
        }, function(err) {
            $scope.showToast('Failed to create claim. Please verify inputs.', 'error');
        });
    };

    $scope.updateClaimStatus = function(claim, newStatus) {
        $http.put('/api/claims/' + claim.id + '/status?status=' + newStatus).then(function(res) {
            claim.status = newStatus;
            $scope.updateMetrics();
            $scope.showToast('Claim #' + claim.id + ' status updated to ' + newStatus, 'success');
        }, function(err) {
            $scope.showToast('Failed to update claim status.', 'error');
        });
    };

    $scope.deleteClaim = function(id) {
        if (!confirm('Are you sure you want to delete Claim #' + id + '?')) return;
        $http.delete('/api/claims/' + id).then(function() {
            $scope.showToast('Claim #' + id + ' deleted.', 'success');
            $scope.loadClaims();
        }, function() {
            $scope.showToast('Failed to delete claim.', 'error');
        });
    };

    $scope.openVehicleModal = function() {
        $scope.newVehicle = { year: new Date().getFullYear() };
        $scope.modals.vehicle = true;
    };

    $scope.submitVehicle = function() {
        $http.post('/api/vehicles', $scope.newVehicle).then(function(res) {
            $scope.showToast('Vehicle registered successfully with VIN ' + res.data.vin, 'success');
            $scope.modals.vehicle = false;
            $scope.loadVehicles();
        }, function() {
            $scope.showToast('Failed to register vehicle.', 'error');
        });
    };

    $scope.deleteVehicle = function(id) {
        if (!confirm('Are you sure you want to delete this vehicle?')) return;
        $http.delete('/api/vehicles/' + id).then(function() {
            $scope.showToast('Vehicle deleted successfully.', 'success');
            $scope.loadVehicles();
        }, function() {
            $scope.showToast('Failed to delete vehicle.', 'error');
        });
    };

    $scope.openContractModal = function() {
        var randomNum = 'CON-' + Math.floor(100000 + Math.random() * 900000);
        $scope.newContract = {
            contractNumber: randomNum,
            startDate: new Date().toISOString().split('T')[0],
            status: 'ACTIVE'
        };
        $scope.modals.contract = true;
    };

    $scope.submitContract = function() {
        $http.post('/api/finance-contracts', $scope.newContract).then(function(res) {
            $scope.showToast('Finance Contract ' + res.data.contractNumber + ' created!', 'success');
            $scope.modals.contract = false;
            $scope.loadContracts();
        }, function() {
            $scope.showToast('Failed to create contract.', 'error');
        });
    };

    $scope.deleteContract = function(id) {
        if (!confirm('Are you sure you want to delete this finance contract?')) return;
        $http.delete('/api/finance-contracts/' + id).then(function() {
            $scope.showToast('Contract deleted successfully.', 'success');
            $scope.loadContracts();
        }, function() {
            $scope.showToast('Failed to delete contract.', 'error');
        });
    };

    $scope.openCustomerModal = function() {
        $scope.newCustomer = {};
        $scope.modals.customer = true;
    };

    $scope.submitCustomer = function() {
        $http.post('/api/customers', $scope.newCustomer).then(function(res) {
            $scope.showToast('Customer ' + res.data.firstName + ' ' + res.data.lastName + ' added!', 'success');
            $scope.modals.customer = false;
            $scope.loadCustomers();
        }, function() {
            $scope.showToast('Failed to add customer.', 'error');
        });
    };

    $scope.deleteCustomer = function(id) {
        if (!confirm('Are you sure you want to delete this customer?')) return;
        $http.delete('/api/customers/' + id).then(function() {
            $scope.showToast('Customer deleted successfully.', 'success');
            $scope.loadCustomers();
        }, function() {
            $scope.showToast('Failed to delete customer.', 'error');
        });
    };

    $scope.filterClaimsByStatus = function(claim) {
        if ($scope.statusFilter === 'ALL') return true;
        var s = (claim.status || '').toUpperCase();
        return s === $scope.statusFilter;
    };

    $scope.loadAll();
}]);
