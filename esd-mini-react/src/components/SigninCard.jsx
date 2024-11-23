import React from 'react';

function SigninCard() {
  return (
    <>
      <div className="signin-container">
        <div className="sign-up">
          <form className="signup-form">
            <h3 id="sign_up_header">Sign In</h3>
            <div className="mb-3">
              <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
              <input
                type="email"
                className="form-control"
                id="exampleInputEmail1"
                placeholder="Email Address"
                aria-describedby="emailHelp"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="exampleInputPassword1" className="form-label">Password</label>
              <input
                type="password"
                className="form-control"
                placeholder="Password"
                id="exampleInputPassword1"
              />
            </div>
            <button type="submit" className="btn btn-primary">Sign In</button>
          </form>
        </div>
      </div>
    </>
  );
}

export default SigninCard;
