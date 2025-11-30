import { Routes, Route, Navigate } from 'react-router-dom'
import { useAuth } from './context/AuthContext'
import LoginPage from './pages/LoginPage'
import ReceptionistDashboard from './pages/ReceptionistDashboard'
import TrainerDashboard from './pages/TrainerDashboard'
import ClientDashboard from './pages/ClientDashboard'
import ProtectedRoute from './components/ProtectedRoute'
import ClientsPage from "./pages/ClientsPage";
import GroupClassesPage from "./pages/GroupClassesPage";
import ReviewsPage from "./pages/ReviewsPage";
import TrainerClassesPage from "./pages/TrainerClassesPage";


function App() {
  const { token, loading } = useAuth()

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-slate-50">
        <div className="text-center">
          <div className="h-8 w-8 animate-spin rounded-full border-4 border-blue-600 border-t-blue-200 mx-auto mb-4"></div>
          <p className="text-slate-600">Loading...</p>
        </div>
      </div>
    )
  }

  return (
    <Routes>
      <Route path="/login" element={!token ? <LoginPage /> : <Navigate to="/dashboard" replace />} />
      
      <Route
        path="/dashboard"
        element={
          token ? (
            <ProtectedRoute>
              <DashboardRouter />
            </ProtectedRoute>
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />
      
      <Route
      path="/dashboard/clients"
      element={
        token ? (
          <ProtectedRoute>
            <ClientsPage />
          </ProtectedRoute>
        ) : (
          <Navigate to="/login" replace />
        )
      }
     />

      <Route
      path="/dashboard/classes"
      element={
        token ? (
          <ProtectedRoute>
            <GroupClassesPage />
          </ProtectedRoute>
        ) : (
          <Navigate to="/login" replace />
        )
      }
      />

      <Route
      path="/dashboard/reviews"
      element={
        token ? (
          <ProtectedRoute>
            <ReviewsPage />
          </ProtectedRoute>
        ) : (
          <Navigate to="/login" replace />
        )
      }
      />

      <Route
        path="/dashboard/trainer/classes"
        element={
          token ? (
            <ProtectedRoute allowedRole="TRAINER">
              <TrainerClassesPage />
            </ProtectedRoute>
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />


      <Route path="/" element={token ? <Navigate to="/dashboard" replace /> : <Navigate to="/login" replace />} />
      <Route path="*" element={<Navigate to={token ? "/dashboard" : "/login"} replace />} />
    </Routes>
  )
}

function DashboardRouter() {
  const { userRole, loading } = useAuth()

  if (loading || !userRole) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-background">
        <div className="text-center">
          <div className="h-8 w-8 animate-spin rounded-full border-4 border-primary border-t-primary/30 mx-auto mb-4"></div>
          <p className="text-muted-foreground">Loading dashboard...</p>
        </div>
      </div>
    )
  }

  switch (userRole) {
    case 'ADMIN':
      return <ReceptionistDashboard />
    case 'TRAINER':
      return <TrainerDashboard />
    case 'CLIENT':
      return <ClientDashboard />
    default:
      return (
        <div className="min-h-screen flex items-center justify-center bg-background">
          <div className="text-center">
            <p className="text-muted-foreground mb-4">Unknown user role: {userRole}</p>
            <p className="text-sm text-muted-foreground">Please contact support</p>
          </div>
        </div>
      )
  }
}

export default App
