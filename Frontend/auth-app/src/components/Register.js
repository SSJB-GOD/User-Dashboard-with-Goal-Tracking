import React, { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import axios from "axios"

function Register() {
  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [error, setError] = useState("")
  const navigate = useNavigate()

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError("")

    try {
      const response = await axios.post("http://localhost:8080/api/register", { name, email, password })
      console.log(response.data)
      navigate("/login")
    } catch (err) {
      setError("Registration failed. Please try again.")
    }
  }

  return (
    <div className="auth-container">
      <h2>Create a new account</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Name</label>
          <input id="name" type="text" value={name} onChange={(e) => setName(e.target.value)} required />
        </div>
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
        <button type="submit">Register</button>
      </form>
      <p className="auth-link">
        Already have an account? <Link to="/login">Sign in</Link>
      </p>
    </div>
  )
}

export default Register

