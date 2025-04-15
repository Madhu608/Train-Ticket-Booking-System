      
    
		    function validateEmail(event) {
			event.preventDefault();
			const emailInput = document.getElementById('email');
			const emailError = document.getElementById('emailError');
			const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailRegex.test(emailInput.value)) {
				emailError.textContent = 'Invalid email format...!';
			} else if (emailInput.value.includes('#') || emailInput.value.includes('$') || emailInput.value.includes('%') || emailInput.value.includes('&')) {
				emailError.textContent = 'Email should not contain #, $, %,!, or & characters.';
			} else {
				emailError.textContent = '';
				emailInput.setCustomValidity('');
				document.forms[0].submit();
			}
		}
	
	

	  
  
  

    

  
  
  



  
  
  
