import React from "react"
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom"
import Login from "./components/Login"
import Register from "./components/Register"
import ForgotPassword from "./components/ForgotPassword"
import Home from "./components/Home"
import "./App.css"

function App() {
  return (
    <Router>
      <div className="app">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />
          <Route path="/home" element={<Home />} />
          <Route path="/" element={<Navigate to="/login" replace />} />
        </Routes>
      </div>
    </Router>
  )
}

export default App

