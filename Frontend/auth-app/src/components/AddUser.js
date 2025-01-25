import React, { useState } from "react"

function AddUser({ onAddUser }) {
  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [goalTitle, setGoalTitle] = useState("")
  const [goalDeadline, setGoalDeadline] = useState("")

  const handleSubmit = (e) => {
    e.preventDefault()
    if (name && email) {
      const newUser = {
        name,
        email,
        goals:
          goalTitle && goalDeadline
            ? [
                {
                  id: 1,
                  title: goalTitle,
                  deadline: goalDeadline,
                  status: "In Progress",
                },
              ]
            : [],
      }
      onAddUser(newUser)
      setName("")
      setEmail("")
      setGoalTitle("")
      setGoalDeadline("")
    }
  }

  return (
    <div className="add-user">
      <h2>Add New User</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Name</label>
          <input id="name" type="text" value={name} onChange={(e) => setName(e.target.value)} required />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input id="email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div className="form-group">
          <label htmlFor="goalTitle">Goal Title</label>
          <input id="goalTitle" type="text" value={goalTitle} onChange={(e) => setGoalTitle(e.target.value)} />
        </div>
        <div className="form-group">
          <label htmlFor="goalDeadline">Goal Deadline</label>
          <input id="goalDeadline" type="date" value={goalDeadline} onChange={(e) => setGoalDeadline(e.target.value)} />
        </div>
        <button type="submit" className="submit-btn">
          Add User
        </button>
      </form>
    </div>
  )
}

export default AddUser

