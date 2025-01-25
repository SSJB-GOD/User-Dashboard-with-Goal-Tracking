import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

function ForgotPassword() {
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');
  const [resetEmailSent, setResetEmailSent] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    if (!email) {
      setError('Please enter your email address');
      return;
    }

    try {
      await axios.post('http://localhost:8080/api/forgot-password', { email });
      setResetEmailSent(true);
    } catch (err) {
      setError('Failed to send password reset email. Please try again.');
    }
  };

  return (
    <div className="auth-container">
      <h2>Forgot Password</h2>
      {!resetEmailSent ? (
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="email">Email address</label>
            <input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          {error && <p className="error">{error}</p>}
          <button type="submit" className="submit-btn">Send Reset Link</button>
        </form>
      ) : (
        <p className="success">Password reset email sent. Please check your inbox.</p>
      )}
      <div className="auth-links">
        <p>
          Remember your password? <Link to="/login">Back to Login</Link>
        </p>
      </div>
    </div>
  );
}

export default ForgotPassword;
