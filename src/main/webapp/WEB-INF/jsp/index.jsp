<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" deferredSyntaxAllowedAsLiteral="true"%>
<!DOCTYPE html>
<html lang="en" ng-app="warrantyWaveApp">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WarrantyWave | Automotive Warranty & Finance Suite</title>
    <meta name="description" content="Next-generation enterprise dashboard for managing warranty claims, vehicle fleets, finance contracts, and customers.">
    
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800&family=Plus+Jakarta+Sans:wght@400;500;600;700&family=JetBrains+Mono:wght@400;500&display=swap" rel="stylesheet">
    
    <!-- Custom Design System -->
    <link rel="stylesheet" href="/css/style.css">

    <!-- AngularJS via CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="/js/app.js"></script>
</head>
<body ng-controller="MainController" class="app-layout">

    <!-- Navigation Header -->
    <header class="navbar">
        <div class="nav-container">
            <a href="/" class="brand" id="brand-logo">
                <div class="brand-icon">&#10038;</div>
                <div class="brand-text">
                    <h1>WarrantyWave</h1>
                    <span>Enterprise Automotive Suite</span>
                </div>
            </a>

            <!-- Navigation Tabs -->
            <nav class="nav-tabs">
                <button id="nav-dashboard" class="nav-tab-btn" ng-class="{'active': currentTab === 'dashboard'}" ng-click="setTab('dashboard')">
                    &#9783; Dashboard
                </button>
                <button id="nav-claims" class="nav-tab-btn" ng-class="{'active': currentTab === 'claims'}" ng-click="setTab('claims')">
                    &#9745; Warranty Claims
                    <span class="nav-badge" ng-if="claims.length">{{ claims.length }}</span>
                </button>
                <button id="nav-vehicles" class="nav-tab-btn" ng-class="{'active': currentTab === 'vehicles'}" ng-click="setTab('vehicles')">
                    &#9951; Vehicles
                    <span class="nav-badge" ng-if="vehicles.length">{{ vehicles.length }}</span>
                </button>
                <button id="nav-contracts" class="nav-tab-btn" ng-class="{'active': currentTab === 'contracts'}" ng-click="setTab('contracts')">
                    &#9776; Contracts
                    <span class="nav-badge" ng-if="contracts.length">{{ contracts.length }}</span>
                </button>
                <button id="nav-customers" class="nav-tab-btn" ng-class="{'active': currentTab === 'customers'}" ng-click="setTab('customers')">
                    &#9881; Customers
                    <span class="nav-badge" ng-if="customers.length">{{ customers.length }}</span>
                </button>
            </nav>

            <!-- Port / Live Status & Mobile Toggle -->
            <div style="display: flex; align-items: center; gap: 0.6rem;">
                <div class="nav-status">
                    <span class="status-dot"></span>
                    <span>Live :8030</span>
                    <button class="btn btn-ghost btn-sm" ng-click="loadAll()" title="Reload All Data" style="margin-left: 0.35rem; padding: 0.15rem 0.45rem;">
                        &#8635;
                    </button>
                </div>

                <!-- Mobile Hamburger Toggle -->
                <button class="mobile-menu-btn" ng-click="toggleMobileMenu()" id="btn-mobile-toggle" aria-label="Toggle Navigation">
                    <span class="bar"></span>
                    <span class="bar"></span>
                    <span class="bar"></span>
                </button>
            </div>
        </div>

        <!-- Mobile Collapsible Navigation Drawer -->
        <div class="mobile-nav-drawer" ng-show="mobileMenuOpen">
            <button class="mobile-drawer-btn" ng-class="{'active': currentTab === 'dashboard'}" ng-click="setTab('dashboard')">
                <span>&#9783; Dashboard Overview</span>
            </button>
            <button class="mobile-drawer-btn" ng-class="{'active': currentTab === 'claims'}" ng-click="setTab('claims')">
                <span>&#9745; Warranty Claims</span>
                <span class="nav-badge" ng-if="claims.length">{{ claims.length }}</span>
            </button>
            <button class="mobile-drawer-btn" ng-class="{'active': currentTab === 'vehicles'}" ng-click="setTab('vehicles')">
                <span>&#9951; Vehicle Fleet</span>
                <span class="nav-badge" ng-if="vehicles.length">{{ vehicles.length }}</span>
            </button>
            <button class="mobile-drawer-btn" ng-class="{'active': currentTab === 'contracts'}" ng-click="setTab('contracts')">
                <span>&#9776; Finance Contracts</span>
                <span class="nav-badge" ng-if="contracts.length">{{ contracts.length }}</span>
            </button>
            <button class="mobile-drawer-btn" ng-class="{'active': currentTab === 'customers'}" ng-click="setTab('customers')">
                <span>&#9881; Customer Registry</span>
                <span class="nav-badge" ng-if="customers.length">{{ customers.length }}</span>
            </button>
            <div style="padding: 0.75rem 0.5rem 0.25rem 0.5rem; display: flex; gap: 0.5rem;">
                <button class="btn btn-primary btn-sm" style="flex: 1;" ng-click="openClaimModal(); closeMobileMenu();">
                    + New Claim
                </button>
                <button class="btn btn-ghost btn-sm" style="flex: 1;" ng-click="openVehicleModal(); closeMobileMenu();">
                    + Vehicle
                </button>
            </div>
        </div>
    </header>

    <!-- Main Content Area -->
    <main class="main-content">

        <!-- ========================================== -->
        <!-- TAB 1: DASHBOARD OVERVIEW                  -->
        <!-- ========================================== -->
        <section id="tab-dashboard" ng-show="currentTab === 'dashboard'">
            <div class="section-header">
                <div class="section-title">
                    <h2>&#9783; System Overview & Metrics</h2>
                    <p>Live health and transactional telemetry across claims, fleet, and contracts.</p>
                </div>
                <div style="display: flex; gap: 0.75rem;">
                    <button id="btn-quick-claim" class="btn btn-primary" ng-click="openClaimModal()">
                        + File New Claim
                    </button>
                    <button id="btn-quick-vehicle" class="btn btn-ghost" ng-click="openVehicleModal()">
                        + Register Vehicle
                    </button>
                </div>
            </div>

            <!-- Metrics Cards Grid -->
            <div class="metrics-grid">
                <div class="metric-card">
                    <div class="metric-icon-wrap">
                        <div class="metric-icon indigo">&#9745;</div>
                        <span class="metric-trend up">&#9650; Active</span>
                    </div>
                    <div class="metric-value">{{ metrics.totalClaims }}</div>
                    <div class="metric-label">Total Warranty Claims</div>
                    <div style="margin-top: 0.75rem; font-size: 0.8rem; color: var(--text-dim); display: flex; gap: 0.8rem;">
                        <span><b style="color: #34d399;">{{ metrics.approvedClaims }}</b> Approved</span>
                        <span><b style="color: #fbbf24;">{{ metrics.submittedClaims }}</b> Pending</span>
                        <span><b style="color: #f87171;">{{ metrics.rejectedClaims }}</b> Rejected</span>
                    </div>
                </div>

                <div class="metric-card">
                    <div class="metric-icon-wrap">
                        <div class="metric-icon cyan">&#9951;</div>
                        <span class="metric-trend up">&#9650; Fleet</span>
                    </div>
                    <div class="metric-value">{{ vehicles.length }}</div>
                    <div class="metric-label">Registered Vehicles</div>
                    <div style="margin-top: 0.75rem; font-size: 0.8rem; color: var(--text-dim);">
                        Automotive units actively tracked in warranty database
                    </div>
                </div>

                <div class="metric-card">
                    <div class="metric-icon-wrap">
                        <div class="metric-icon emerald">&#9776;</div>
                        <span class="metric-trend up">&#9650; Active</span>
                    </div>
                    <div class="metric-value">{{ contracts.length }}</div>
                    <div class="metric-label">Finance Contracts</div>
                    <div style="margin-top: 0.75rem; font-size: 0.8rem; color: var(--text-dim);">
                        Active leasing and finance loan arrangements
                    </div>
                </div>

                <div class="metric-card">
                    <div class="metric-icon-wrap">
                        <div class="metric-icon amber">&#9881;</div>
                        <span class="metric-trend up">&#9650; Clients</span>
                    </div>
                    <div class="metric-value">{{ customers.length }}</div>
                    <div class="metric-label">Registered Customers</div>
                    <div style="margin-top: 0.75rem; font-size: 0.8rem; color: var(--text-dim);">
                        Verified policyholders & vehicle owners
                    </div>
                </div>
            </div>

            <!-- Recent Claims Quick Table -->
            <div class="glass-card">
                <div class="card-toolbar">
                    <h3>Recent Warranty Submissions</h3>
                    <button class="btn btn-ghost btn-sm" ng-click="setTab('claims')">View All Claims &rarr;</button>
                </div>

                <div class="table-responsive">
                    <table class="custom-table">
                        <thead>
                            <tr>
                                <th>Claim ID</th>
                                <th>Date</th>
                                <th>Vehicle & Contract</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Description</th>
                                <th style="text-align: right;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="claim in claims | limitTo:5">
                                <td><span class="badge-id">&#35;{{ claim.id }}</span></td>
                                <td>{{ claim.claimDate || 'N/A' }}</td>
                                <td>
                                    <div><b>Veh &#35;{{ claim.vehicleId }}</b></div>
                                    <small style="color: var(--text-dim);">Contract &#35;{{ claim.contractId }}</small>
                                </td>
                                <td><b>{{ claim.claimAmount | currency }}</b></td>
                                <td>
                                    <span class="badge" ng-class="{
                                        'badge-approved': (claim.status | uppercase) === 'APPROVED',
                                        'badge-rejected': (claim.status | uppercase) === 'REJECTED',
                                        'badge-submitted': (claim.status | uppercase) !== 'APPROVED' && (claim.status | uppercase) !== 'REJECTED'
                                    }">
                                        {{ claim.status || 'SUBMITTED' }}
                                    </span>
                                </td>
                                <td style="max-width: 250px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                                    {{ claim.description || 'No description provided' }}
                                </td>
                                <td style="text-align: right;">
                                    <button class="btn btn-success btn-sm" ng-if="(claim.status | uppercase) !== 'APPROVED'" ng-click="updateClaimStatus(claim, 'APPROVED')">
                                        &#10003; Approve
                                    </button>
                                    <button class="btn btn-danger btn-sm" ng-if="(claim.status | uppercase) !== 'REJECTED'" ng-click="updateClaimStatus(claim, 'REJECTED')">
                                        &#10005; Reject
                                    </button>
                                </td>
                            </tr>
                            <tr ng-if="!claims.length">
                                <td colspan="7" class="empty-state">
                                    <div class="empty-icon">&#128203;</div>
                                    <p>No warranty claims found in the database.</p>
                                    <button class="btn btn-primary btn-sm" style="margin-top: 0.75rem;" ng-click="openClaimModal()">
                                        File First Claim
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>


        <!-- ========================================== -->
        <!-- TAB 2: WARRANTY CLAIMS MANAGEMENT          -->
        <!-- ========================================== -->
        <section id="tab-claims" ng-show="currentTab === 'claims'">
            <div class="section-header">
                <div class="section-title">
                    <h2>&#9745; Warranty Claims Management</h2>
                    <p>Track, approve, reject, and adjust warranty repair claims.</p>
                </div>
                <button id="btn-add-claim" class="btn btn-primary" ng-click="openClaimModal()">
                    + Submit New Claim
                </button>
            </div>

            <div class="glass-card">
                <div class="card-toolbar">
                    <!-- Search Input -->
                    <div class="search-box">
                        <span class="search-icon">&#128269;</span>
                        <input type="text" class="search-input" placeholder="Search claims by ID, desc..." ng-model="claimSearch">
                    </div>

                    <!-- Filter Pills -->
                    <div class="filter-group">
                        <button class="filter-pill" ng-class="{'active': statusFilter === 'ALL'}" ng-click="statusFilter = 'ALL'">All</button>
                        <button class="filter-pill" ng-class="{'active': statusFilter === 'SUBMITTED'}" ng-click="statusFilter = 'SUBMITTED'">Submitted / Pending</button>
                        <button class="filter-pill" ng-class="{'active': statusFilter === 'APPROVED'}" ng-click="statusFilter = 'APPROVED'">Approved</button>
                        <button class="filter-pill" ng-class="{'active': statusFilter === 'REJECTED'}" ng-click="statusFilter = 'REJECTED'">Rejected</button>
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="custom-table" id="claims-table">
                        <thead>
                            <tr>
                                <th>Claim ID</th>
                                <th>Customer ID</th>
                                <th>Vehicle ID</th>
                                <th>Contract ID</th>
                                <th>Date</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Description / Remarks</th>
                                <th style="text-align: right;">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="claim in claims | filter:claimSearch | filter:filterClaimsByStatus">
                                <td><span class="badge-id">&#35;{{ claim.id }}</span></td>
                                <td>Cust &#35;{{ claim.customerId }}</td>
                                <td>Veh &#35;{{ claim.vehicleId }}</td>
                                <td>Con &#35;{{ claim.contractId }}</td>
                                <td>{{ claim.claimDate }}</td>
                                <td><b>{{ claim.claimAmount | currency }}</b></td>
                                <td>
                                    <span class="badge" ng-class="{
                                        'badge-approved': (claim.status | uppercase) === 'APPROVED',
                                        'badge-rejected': (claim.status | uppercase) === 'REJECTED',
                                        'badge-submitted': (claim.status | uppercase) !== 'APPROVED' && (claim.status | uppercase) !== 'REJECTED'
                                    }">
                                        {{ claim.status || 'SUBMITTED' }}
                                    </span>
                                </td>
                                <td>
                                    <div>{{ claim.description || 'No description' }}</div>
                                    <small style="color: var(--text-dim);" ng-if="claim.remarks">Note: {{ claim.remarks }}</small>
                                </td>
                                <td style="text-align: right; white-space: nowrap;">
                                    <button class="btn btn-success btn-sm" ng-if="(claim.status | uppercase) !== 'APPROVED'" ng-click="updateClaimStatus(claim, 'APPROVED')" title="Approve Claim">
                                        &#10003;
                                    </button>
                                    <button class="btn btn-danger btn-sm" ng-if="(claim.status | uppercase) !== 'REJECTED'" ng-click="updateClaimStatus(claim, 'REJECTED')" title="Reject Claim">
                                        &#10005;
                                    </button>
                                    <button class="btn btn-ghost btn-sm" ng-click="deleteClaim(claim.id)" title="Delete Claim" style="color: var(--danger);">
                                        &#128465;
                                    </button>
                                </td>
                            </tr>
                            <tr ng-if="!claims.length">
                                <td colspan="9" class="empty-state">
                                    <div class="empty-icon">&#128203;</div>
                                    <p>No warranty claims available.</p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>


        <!-- ========================================== -->
        <!-- TAB 3: VEHICLE FLEET MANAGEMENT           -->
        <!-- ========================================== -->
        <section id="tab-vehicles" ng-show="currentTab === 'vehicles'">
            <div class="section-header">
                <div class="section-title">
                    <h2>&#9951; Vehicle Fleet Management</h2>
                    <p>Track registered cars, VINs, specifications, and client ownership.</p>
                </div>
                <button id="btn-add-vehicle" class="btn btn-primary" ng-click="openVehicleModal()">
                    + Register Vehicle
                </button>
            </div>

            <div class="glass-card">
                <div class="card-toolbar">
                    <div class="search-box">
                        <span class="search-icon">&#128269;</span>
                        <input type="text" class="search-input" placeholder="Search by VIN, Make, Model..." ng-model="vehicleSearch">
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="custom-table" id="vehicles-table">
                        <thead>
                            <tr>
                                <th>Vehicle ID</th>
                                <th>VIN</th>
                                <th>Make & Model</th>
                                <th>Year</th>
                                <th>License Plate</th>
                                <th>Mileage</th>
                                <th>Owner (Customer ID)</th>
                                <th style="text-align: right;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="veh in vehicles | filter:vehicleSearch">
                                <td><span class="badge-id">&#35;{{ veh.id }}</span></td>
                                <td><span class="badge-id" style="color: var(--secondary);">{{ veh.vin }}</span></td>
                                <td><b>{{ veh.make }}</b> {{ veh.model }}</td>
                                <td>{{ veh.year }}</td>
                                <td><span class="badge" style="background: rgba(255,255,255,0.06);">{{ veh.licensePlate || 'N/A' }}</span></td>
                                <td>{{ veh.mileage | number }} miles</td>
                                <td>Cust &#35;{{ veh.customerId || 'N/A' }}</td>
                                <td style="text-align: right;">
                                    <button class="btn btn-ghost btn-sm" ng-click="deleteVehicle(veh.id)" style="color: var(--danger);">
                                        &#128465; Delete
                                    </button>
                                </td>
                            </tr>
                            <tr ng-if="!vehicles.length">
                                <td colspan="8" class="empty-state">
                                    <div class="empty-icon">&#128663;</div>
                                    <p>No vehicles registered in fleet.</p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>


        <!-- ========================================== -->
        <!-- TAB 4: FINANCE CONTRACTS                   -->
        <!-- ========================================== -->
        <section id="tab-contracts" ng-show="currentTab === 'contracts'">
            <div class="section-header">
                <div class="section-title">
                    <h2>&#9776; Finance Contracts</h2>
                    <p>Manage loan amounts, monthly schedules, interest terms, and down payments.</p>
                </div>
                <button id="btn-add-contract" class="btn btn-primary" ng-click="openContractModal()">
                    + New Finance Contract
                </button>
            </div>

            <div class="glass-card">
                <div class="card-toolbar">
                    <div class="search-box">
                        <span class="search-icon">&#128269;</span>
                        <input type="text" class="search-input" placeholder="Search by contract number..." ng-model="contractSearch">
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="custom-table" id="contracts-table">
                        <thead>
                            <tr>
                                <th>Contract #</th>
                                <th>Customer & Vehicle</th>
                                <th>Loan Amount</th>
                                <th>Down Payment</th>
                                <th>Interest Rate</th>
                                <th>Term</th>
                                <th>Monthly Due</th>
                                <th>Status</th>
                                <th style="text-align: right;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="con in contracts | filter:contractSearch">
                                <td><span class="badge-id" style="color: #a78bfa;">{{ con.contractNumber }}</span></td>
                                <td>
                                    <div>Cust &#35;{{ con.customerId }}</div>
                                    <small style="color: var(--text-dim);">Veh &#35;{{ con.vehicleId }}</small>
                                </td>
                                <td><b>{{ con.loanAmount | currency }}</b></td>
                                <td>{{ con.downPayment | currency }}</td>
                                <td>{{ con.interestRate }}%</td>
                                <td>{{ con.termMonths }} mo</td>
                                <td><b style="color: #34d399;">{{ con.monthlyPayment | currency }}</b></td>
                                <td>
                                    <span class="badge badge-active">{{ con.status || 'ACTIVE' }}</span>
                                </td>
                                <td style="text-align: right;">
                                    <button class="btn btn-ghost btn-sm" ng-click="deleteContract(con.id)" style="color: var(--danger);">
                                        &#128465;
                                    </button>
                                </td>
                            </tr>
                            <tr ng-if="!contracts.length">
                                <td colspan="9" class="empty-state">
                                    <div class="empty-icon">&#128179;</div>
                                    <p>No finance contracts created yet.</p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>


        <!-- ========================================== -->
        <!-- TAB 5: CUSTOMER DIRECTORY                  -->
        <!-- ========================================== -->
        <section id="tab-customers" ng-show="currentTab === 'customers'">
            <div class="section-header">
                <div class="section-title">
                    <h2>&#9881; Customer Directory</h2>
                    <p>Manage customer profiles, contact info, and residency records.</p>
                </div>
                <button id="btn-add-customer" class="btn btn-primary" ng-click="openCustomerModal()">
                    + Add New Customer
                </button>
            </div>

            <div class="glass-card">
                <div class="card-toolbar">
                    <div class="search-box">
                        <span class="search-icon">&#128269;</span>
                        <input type="text" class="search-input" placeholder="Search customer by name, email..." ng-model="customerSearch">
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="custom-table" id="customers-table">
                        <thead>
                            <tr>
                                <th>Customer ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Location</th>
                                <th style="text-align: right;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="cust in customers | filter:customerSearch">
                                <td><span class="badge-id">&#35;{{ cust.id }}</span></td>
                                <td><b>{{ cust.firstName }} {{ cust.lastName }}</b></td>
                                <td><a href="mailto:{{ cust.email }}" style="color: var(--secondary); text-decoration: none;">{{ cust.email }}</a></td>
                                <td>{{ cust.phoneNumber || 'N/A' }}</td>
                                <td>{{ cust.city }}<span ng-if="cust.state">, {{ cust.state }}</span></td>
                                <td style="text-align: right;">
                                    <button class="btn btn-ghost btn-sm" ng-click="deleteCustomer(cust.id)" style="color: var(--danger);">
                                        &#128465;
                                    </button>
                                </td>
                            </tr>
                            <tr ng-if="!customers.length">
                                <td colspan="6" class="empty-state">
                                    <div class="empty-icon">&#128101;</div>
                                    <p>No customers recorded.</p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>

    </main>


    <!-- ========================================== -->
    <!-- MODAL 1: SUBMIT CLAIM                      -->
    <!-- ========================================== -->
    <div class="modal-overlay" ng-if="modals.claim">
        <div class="modal-card">
            <div class="modal-header">
                <h3>Submit Warranty Claim</h3>
                <button class="btn-close" ng-click="modals.claim = false">&times;</button>
            </div>
            <form ng-submit="submitClaim()">
                <div class="modal-body">
                    <div class="form-grid">
                        <div class="form-group">
                            <label class="form-label">Customer ID *</label>
                            <input type="number" class="form-control" ng-model="newClaim.customerId" required placeholder="e.g. 1">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Vehicle ID *</label>
                            <input type="number" class="form-control" ng-model="newClaim.vehicleId" required placeholder="e.g. 1">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Contract ID *</label>
                            <input type="number" class="form-control" ng-model="newClaim.contractId" required placeholder="e.g. 1">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Claim Amount ($) *</label>
                            <input type="number" step="0.01" class="form-control" ng-model="newClaim.claimAmount" required placeholder="1250.00">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Claim Date</label>
                            <input type="date" class="form-control" ng-model="newClaim.claimDate">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Initial Status</label>
                            <select class="form-control" ng-model="newClaim.status">
                                <option value="Submitted">Submitted</option>
                                <option value="APPROVED">APPROVED</option>
                                <option value="REJECTED">REJECTED</option>
                            </select>
                        </div>
                        <div class="form-group full-width">
                            <label class="form-label">Claim Description *</label>
                            <textarea class="form-control" rows="3" ng-model="newClaim.description" required placeholder="Describe issue (e.g. transmission slippage during acceleration)"></textarea>
                        </div>
                        <div class="form-group full-width">
                            <label class="form-label">Remarks / Technician Notes</label>
                            <input type="text" class="form-control" ng-model="newClaim.remarks" placeholder="Optional inspector notes">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-ghost" ng-click="modals.claim = false">Cancel</button>
                    <button type="submit" class="btn btn-primary">Submit Claim</button>
                </div>
            </form>
        </div>
    </div>


    <!-- ========================================== -->
    <!-- MODAL 2: REGISTER VEHICLE                  -->
    <!-- ========================================== -->
    <div class="modal-overlay" ng-if="modals.vehicle">
        <div class="modal-card">
            <div class="modal-header">
                <h3>Register Vehicle</h3>
                <button class="btn-close" ng-click="modals.vehicle = false">&times;</button>
            </div>
            <form ng-submit="submitVehicle()">
                <div class="modal-body">
                    <div class="form-grid">
                        <div class="form-group full-width">
                            <label class="form-label">Vehicle Identification Number (VIN) *</label>
                            <input type="text" class="form-control" ng-model="newVehicle.vin" required placeholder="17-character VIN (e.g. 1HGCR2F83HA123456)">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Make *</label>
                            <input type="text" class="form-control" ng-model="newVehicle.make" required placeholder="e.g. Toyota, BMW">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Model *</label>
                            <input type="text" class="form-control" ng-model="newVehicle.model" required placeholder="e.g. Camry, M3">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Year *</label>
                            <input type="number" class="form-control" ng-model="newVehicle.year" required placeholder="2024">
                        </div>
                        <div class="form-group">
                            <label class="form-label">License Plate</label>
                            <input type="text" class="form-control" ng-model="newVehicle.licensePlate" placeholder="e.g. ABC-1234">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Current Mileage (miles)</label>
                            <input type="number" step="0.1" class="form-control" ng-model="newVehicle.mileage" placeholder="24500">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Owner (Customer ID)</label>
                            <input type="number" class="form-control" ng-model="newVehicle.customerId" placeholder="e.g. 1">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-ghost" ng-click="modals.vehicle = false">Cancel</button>
                    <button type="submit" class="btn btn-primary">Save Vehicle</button>
                </div>
            </form>
        </div>
    </div>


    <!-- ========================================== -->
    <!-- MODAL 3: CREATE CONTRACT                   -->
    <!-- ========================================== -->
    <div class="modal-overlay" ng-if="modals.contract">
        <div class="modal-card">
            <div class="modal-header">
                <h3>New Finance Contract</h3>
                <button class="btn-close" ng-click="modals.contract = false">&times;</button>
            </div>
            <form ng-submit="submitContract()">
                <div class="modal-body">
                    <div class="form-grid">
                        <div class="form-group">
                            <label class="form-label">Contract Number *</label>
                            <input type="text" class="form-control" ng-model="newContract.contractNumber" required>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Customer ID *</label>
                            <input type="number" class="form-control" ng-model="newContract.customerId" required placeholder="1">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Vehicle ID *</label>
                            <input type="number" class="form-control" ng-model="newContract.vehicleId" required placeholder="1">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Loan Amount ($) *</label>
                            <input type="number" step="0.01" class="form-control" ng-model="newContract.loanAmount" required placeholder="28000.00">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Down Payment ($)</label>
                            <input type="number" step="0.01" class="form-control" ng-model="newContract.downPayment" placeholder="3000.00">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Interest Rate (%)</label>
                            <input type="number" step="0.01" class="form-control" ng-model="newContract.interestRate" placeholder="4.99">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Term (Months)</label>
                            <input type="number" class="form-control" ng-model="newContract.termMonths" placeholder="48">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Monthly Payment ($)</label>
                            <input type="number" step="0.01" class="form-control" ng-model="newContract.monthlyPayment" placeholder="520.00">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-ghost" ng-click="modals.contract = false">Cancel</button>
                    <button type="submit" class="btn btn-primary">Create Contract</button>
                </div>
            </form>
        </div>
    </div>


    <!-- ========================================== -->
    <!-- MODAL 4: ADD CUSTOMER                      -->
    <!-- ========================================== -->
    <div class="modal-overlay" ng-if="modals.customer">
        <div class="modal-card">
            <div class="modal-header">
                <h3>Add Customer</h3>
                <button class="btn-close" ng-click="modals.customer = false">&times;</button>
            </div>
            <form ng-submit="submitCustomer()">
                <div class="modal-body">
                    <div class="form-grid">
                        <div class="form-group">
                            <label class="form-label">First Name *</label>
                            <input type="text" class="form-control" ng-model="newCustomer.firstName" required placeholder="John">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Last Name *</label>
                            <input type="text" class="form-control" ng-model="newCustomer.lastName" required placeholder="Doe">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Email Address *</label>
                            <input type="email" class="form-control" ng-model="newCustomer.email" required placeholder="john.doe@example.com">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Phone Number</label>
                            <input type="text" class="form-control" ng-model="newCustomer.phoneNumber" placeholder="+1 (555) 123-4567">
                        </div>
                        <div class="form-group full-width">
                            <label class="form-label">Street Address</label>
                            <input type="text" class="form-control" ng-model="newCustomer.address" placeholder="123 Main St, Apt 4B">
                        </div>
                        <div class="form-group">
                            <label class="form-label">City</label>
                            <input type="text" class="form-control" ng-model="newCustomer.city" placeholder="New York">
                        </div>
                        <div class="form-group">
                            <label class="form-label">State / Zip</label>
                            <div style="display: flex; gap: 0.5rem;">
                                <input type="text" class="form-control" ng-model="newCustomer.state" placeholder="NY" style="width: 40%;">
                                <input type="text" class="form-control" ng-model="newCustomer.zipCode" placeholder="10001" style="width: 60%;">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-ghost" ng-click="modals.customer = false">Cancel</button>
                    <button type="submit" class="btn btn-primary">Save Customer</button>
                </div>
            </form>
        </div>
    </div>


    <!-- Floating Toast Notifications -->
    <div class="toast-container">
        <div class="toast" ng-repeat="toast in toasts" ng-class="{'toast-success': toast.type === 'success', 'toast-error': toast.type === 'error'}">
            <span>{{ toast.message }}</span>
        </div>
    </div>

    <!-- Mobile Bottom Navigation Dock (Phones) -->
    <nav class="mobile-bottom-nav">
        <button class="bottom-nav-item" ng-class="{'active': currentTab === 'dashboard'}" ng-click="setTab('dashboard')" id="bnav-dashboard">
            <span class="bnav-icon">&#9783;</span>
            <span class="bnav-label">Home</span>
        </button>
        <button class="bottom-nav-item" ng-class="{'active': currentTab === 'claims'}" ng-click="setTab('claims')" id="bnav-claims">
            <span class="bnav-icon">&#9745;</span>
            <span class="bnav-label">Claims</span>
            <span class="bnav-badge" ng-if="claims.length">{{ claims.length }}</span>
        </button>
        <button class="bottom-nav-item" ng-class="{'active': currentTab === 'vehicles'}" ng-click="setTab('vehicles')" id="bnav-vehicles">
            <span class="bnav-icon">&#9951;</span>
            <span class="bnav-label">Fleet</span>
        </button>
        <button class="bottom-nav-item" ng-class="{'active': currentTab === 'contracts'}" ng-click="setTab('contracts')" id="bnav-contracts">
            <span class="bnav-icon">&#9776;</span>
            <span class="bnav-label">Contracts</span>
        </button>
        <button class="bottom-nav-item" ng-class="{'active': currentTab === 'customers'}" ng-click="setTab('customers')" id="bnav-customers">
            <span class="bnav-icon">&#9881;</span>
            <span class="bnav-label">Clients</span>
        </button>
    </nav>

</body>
</html>
