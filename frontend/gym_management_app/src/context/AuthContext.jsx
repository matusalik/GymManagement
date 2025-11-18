import { createContext, useContext, useState, useEffect } from 'react'
import { jwtDecode } from 'jwt-decode'

const AuthContext = createContext()

export function AuthProvider({ children }) {
  const [token, setToken] = useState(null)
  const [userRole, setUserRole] = useState(null)
  const [user, setUser] = useState(null)
  const [loading, setLoading] = useState(true)

  // Load token from localStorage on mount
  useEffect(() => {
    const savedToken = localStorage.getItem('authToken')
    if (savedToken) {
      try {
        const decoded = jwtDecode(savedToken)
        console.log('[v0] Token decoded on mount:', decoded)
        setToken(savedToken)
        setUserRole(decoded.role || decoded.roles?.[0])
        setUser({
          username: decoded.username || decoded.sub,
          email: decoded.email,
        })
      } catch (error) {
        console.error('[v0] Invalid token on mount:', error)
        localStorage.removeItem('authToken')
      }
    }
    setLoading(false)
  }, [])

  const login = async (username, password) => {
    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      })

      if (!response.ok) {
        throw new Error('Login failed')
      }

      const data = await response.json()
      console.log('[v0] Login response:', data)
      const jwtToken = data.token || data.jwt || data.accessToken

      if (!jwtToken) {
        throw new Error('No token received from server')
      }

      const decoded = jwtDecode(jwtToken)
      console.log('[v0] Decoded token:', decoded)
      
      const role = decoded.role || decoded.roles?.[0]
      console.log('[v0] Extracted role:', role)
      
      setToken(jwtToken)
      setUserRole(role)
      setUser({
        username: decoded.username || decoded.sub,
        email: decoded.email,
      })
      
      localStorage.setItem('authToken', jwtToken)
      return { success: true }
    } catch (error) {
      console.error('[v0] Login error:', error)
      return { success: false, error: error.message }
    }
  }

  const logout = () => {
    setToken(null)
    setUserRole(null)
    setUser(null)
    localStorage.removeItem('authToken')
  }

  return (
    <AuthContext.Provider value={{ token, userRole, user, loading, login, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider')
  }
  return context
}
