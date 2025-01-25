import React, { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import axios from "axios"

function Login() {
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [error, setError] = useState("")
  const navigate = useNavigate()

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError("")

    try {
      const response = await axios.post("http://localhost:8080/api/login", { email, password })
      console.log(response.data)
      localStorage.setItem("token", response.data.token)
      navigate("/home")
    } catch (err) {
      setError("Invalid email or password")
    }
  }

  return (
    <div className="auth-container">
      <h2>Sign in to your account</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="email">Email address</label>
          <input id="email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            id="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <p className="error">{error}</p>}
        <button type="submit" className="submit-btn">
          Sign in
        </button>
      </form>
      <div className="auth-links">
        <Link to="/forgot-password" className="forgot-password-link">
          Forgot your password?
        </Link>
        <p>
          Don't have an account? <Link to="/register">Create a new account</Link>
        </p>
      </div>
    </div>
  )
}

export default Login

