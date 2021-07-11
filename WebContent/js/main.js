// Link to APIs
// Dummy data -> https://api.npoint.io/7b0141266d09f17a254a

// To track the page number for pagination
var page = 1;

// Get JSON data from an API using jquery
$.getJSON("http://localhost:8080/H2HBABBA1602/fetch_SQL_data.do?page=1", function(data) {

    // Loading the data to HTML table
    let table = document.getElementById('main_table');
    let i = 1;
    data.forEach(function (value) {

        let tr = document.createElement('tr');

        // Hard-coding checkboxes
        let td = document.createElement('td');
        td.setAttribute('class', 'checkbox_all');
        td.innerHTML = '<input value="' + i++ + '" type="checkbox" name="checkbox_row" class="checkbox_all"/>';
        tr.appendChild(td);

        // Storing every row to the HTML table
        td = document.createElement('td');
        td.innerHTML = value["name"];
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = value["customerID"];
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = value["invoiceID"];
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = value["final_amount"];
        tr.appendChild(td);

        td = document.createElement('td');
        td.setAttribute('class', 'due');
        td.innerHTML = value["due_date"];
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = value["payment_date"];
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = value["notes"];
        tr.appendChild(td);
        
        table.appendChild(tr);
    });
    
    // Due dates less than current date are marked red
    $(function () {
    	let today = new Date();
    	$('.due').each(function() {
    		let val = new Date($(this).text());
    		if (val < today) {
    			$(this).addClass('due_red');
    		}
    	});
    });
});



// Pagination
// Left navigation
window.onload = function() {
	
	document.getElementById('left_page').onclick = function() {
		if (page > 1) {
			$('td').remove();
			
			page = page - 1;
			$.getJSON("http://localhost:8080/H2HBABBA1602/fetch_SQL_data.do?page=" + page, function(data) {

			    // Loading the data to HTML table
			    let table = document.getElementById('main_table');
			    let j = 0;
			    data.forEach(function (value) {

			        let tr = document.createElement('tr');

			        // Hard-coding checkboxes
			        let td = document.createElement('td');
			        td.setAttribute('class', 'checkbox_all');
			        td.innerHTML = '<input value="' + j++ + '" type="checkbox" name="checkbox_row" class="checkbox_all"/>';
			        tr.appendChild(td);

			        // Storing every row to the HTML table
			        td = document.createElement('td');
			        td.innerHTML = value["name"];
			        tr.appendChild(td);

			        td = document.createElement('td');
			        td.innerHTML = value["customerID"];
			        tr.appendChild(td);

			        td = document.createElement('td');
			        td.innerHTML = value["invoiceID"];
			        tr.appendChild(td);

			        td = document.createElement('td');
			        td.innerHTML = value["final_amount"];
			        tr.appendChild(td);

			        td = document.createElement('td');
			        td.setAttribute('class', 'due');
			        td.innerHTML = value["due_date"];
			        tr.appendChild(td);

			        td = document.createElement('td');
			        td.innerHTML = value["payment_date"];
			        tr.appendChild(td);

			        td = document.createElement('td');
			        td.innerHTML = value["notes"];
			        tr.appendChild(td);
			        
			        table.appendChild(tr);
			    });
			    
			    // Due dates less than current date are marked red
				$(function () {
					let today = new Date();
					$('.due').each(function() {
						let val = new Date($(this).text());
						if (val < today) {
							$(this).addClass('due_red');
						}
					});
				});
				
				// Track record of the selected checkboxes 
				var checkboxes = document.querySelectorAll("input[type=checkbox][name=checkbox_row]");
				var enable = document.getElementById("edit_btn");
				var delete_enable = document.getElementById("delete_btn");
				var checked = [];
				
				checkboxes.forEach(function(checkbox) {
					checkbox.addEventListener('change', function() {
					checked = Array.from(checkboxes).filter(i => i.checked).map(i => i.value);
					console.log(checked.length);
					
					if(checked.length >= 1)
						delete_enable.disabled = false;
					else
						delete_enable.disabled = true;
					
					if (checked.length == 1)
						enable.disabled = false;
					else
						enable.disabled = true;
					
					});
				});
			});
		};
	};
	
	// Right navigation
	document.getElementById('right_page').onclick = function() {
		
		$('td').remove();
		
		page = page + 1;
		$.getJSON("http://localhost:8080/H2HBABBA1602/fetch_SQL_data.do?page=" + page, function(data) {

			// Loading the data to HTML table
			let table = document.getElementById('main_table');
			let k = 1;
			data.forEach(function (value) {

				let tr = document.createElement('tr');
	
				// Hard-coding checkboxes
				let td = document.createElement('td');
				td.setAttribute('class', 'checkbox_all');
				td.innerHTML = '<input value="' + k++ + '" type="checkbox" name="checkbox_row" class="checkbox_all"/>';
				tr.appendChild(td);
	
				// Storing every row to the HTML table
				td = document.createElement('td');
				td.innerHTML = value["name"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["customerID"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["invoiceID"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["final_amount"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.setAttribute('class', 'due');
				td.innerHTML = value["due_date"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["payment_date"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["notes"];
				tr.appendChild(td);
	        
				table.appendChild(tr);
			});
			
			// Due dates less than current date are marked red
			$(function () {
				let today = new Date();
				$('.due').each(function() {
					let val = new Date($(this).text());
					if (val < today) {
						$(this).addClass('due_red');
					}
				});
			});
			
			// Track record of the selected checkboxes 
			var checkboxes = document.querySelectorAll("input[type=checkbox][name=checkbox_row]");
			var enable = document.getElementById("edit_btn");
			var delete_enable = document.getElementById("delete_btn");
			var checked = [];
			
			checkboxes.forEach(function(checkbox) {
				checkbox.addEventListener('change', function() {
				checked = Array.from(checkboxes).filter(i => i.checked).map(i => i.value);
				console.log(checked.length);
				
				if(checked.length >= 1)
					delete_enable.disabled = false;
				else
					delete_enable.disabled = true;
				
				if (checked.length == 1)
					enable.disabled = false;
				else
					enable.disabled = true;
				
				});
			});
		});
	};
	
	
	
	// Add modal
	// Get the add modal
	var modal = document.getElementById("add_modal");

	// Get the button, span close, clear
	var btn = document.getElementById("add_btn");
	var span = document.getElementsByClassName("close")[0];
	var cancel = document.getElementById("cancel_modal");
	var clear = document.getElementById("clear_model_button");
	var form = document.getElementById("add_form_modal");

	// Opens the model
	btn.onclick = function() {
		modal.style.display = "block";
	};
	
	// Clear the form
	clear.onclick = function() {
		document.getElementById('add_form_modal').reset();
	};
	
	// Submit the add form and redirect to the previous tab
	$('#add_modal_button_id').click(function(event) {
		form.submit();
		window.open("http://localhost:8080/H2HBABBA1602");
	});
	
	// Close the modal
	span.onclick = function() {
		modal.style.display = "none";
	};
	
	cancel.onclick = function() {
		modal.style.display = "none";
	};
	
	
	
	// Track record of the selected checkboxes 
	var checkboxes = document.querySelectorAll("input[type=checkbox][name=checkbox_row]");
	var enable = document.getElementById("edit_btn");
	var delete_enable = document.getElementById("delete_btn");
	var checked = [];
	
	checkboxes.forEach(function(checkbox) {
		checkbox.addEventListener('change', function() {
		checked = Array.from(checkboxes).filter(i => i.checked).map(i => i.value);
		console.log(checked.length);
		
		if(checked.length >= 1)
			delete_enable.disabled = false;
		else
			delete_enable.disabled = true;
		
		if (checked.length == 1)
			enable.disabled = false;
		else
			enable.disabled = true;
		
		});
	});
	
	
	
	
	// Edit modal
	// Get the edit modal
	var emodal = document.getElementById("edit_modal");

	// Get the button, span close, clear
	var ebtn = document.getElementById("edit_btn");
	var espan = document.getElementById("edit_close");
	var ecancel = document.getElementById("edit_cancel_modal");
	var eclear = document.getElementById("edit_clear_model_button");
	
	// Opens the model
	ebtn.onclick = function() {
		emodal.style.display = "block";
	};
	
	// Clear the form
	eclear.onclick = function() {
		document.getElementById('edit_invoice').value='';
		document.getElementById('edit_notes').value='';
	};
	
	// Submit to the servlet
	$('#edit_modal_button_id').click(function(event) {
		let invoice_amount = document.getElementById('edit_invoice').value;
		let notes = document.getElementById('edit_notes').value;
		
		fetch('http://localhost:8080/H2HBABBA1602/edit_SQL_data.do?page=' + page + '&checkbox=' + checked + '&total_open_amount=' + invoice_amount + '&notes=' + notes);
		emodal.style.display = "none";
	});
	
	// Close the modal
	espan.onclick = function() {
		emodal.style.display = "none";
	};
	
	ecancel.onclick = function() {
		emodal.style.display = "none";
	};
	
	
	
	
	// Delete modal
	// Get the delete modal
	var dmodal = document.getElementById("delete_modal");

	// Get the button, span close, clear
	var dbtn = document.getElementById("delete_btn");
	var dspan = document.getElementById("delete_close");
	var dcancel = document.getElementById("delete_cancel_modal");
	
	// Opens the model
	dbtn.onclick = function() {
		dmodal.style.display = "block";
	};
	
	// Submit to the servlet
	$('#delete_modal_button_id').click(function(event) {
		fetch('http://localhost:8080/H2HBABBA1602/delete_SQL_data.do?page=' + page + '&checkbox=' + checked);
		dmodal.style.display = "none";
	});
	
	// Close the modal
	dspan.onclick = function() {
		dmodal.style.display = "none";
	};
	
	dcancel.onclick = function() {
		dmodal.style.display = "none";
	};
	
	
	
	
	// Search
	// Get the search button
	var search_btn = document.getElementById("search_btn");
	
	// Get the value and send it to servlet
	search_btn.onclick = function() {
		let invoice_id = document.getElementById("searchbox_value").value;
		
		$('td').remove();
		$.getJSON("http://localhost:8080/H2HBABBA1602/search_SQL_data.do?invoice_id=" + invoice_id, function(data) {

			// Loading the data to HTML table
			let table = document.getElementById('main_table');
			let m = 1;
			var length = 0;
			data.forEach(function (value) {

				let tr = document.createElement('tr');
	
				// Hard-coding checkboxes
				let td = document.createElement('td');
				td.setAttribute('class', 'checkbox_all');
				td.innerHTML = '<input value="' + m++ + '" type="checkbox" name="checkbox_row" class="checkbox_all"/>';
				tr.appendChild(td);
	
				// Storing every row to the HTML table
				td = document.createElement('td');
				td.innerHTML = value["name"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["customerID"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["invoiceID"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["final_amount"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.setAttribute('class', 'due');
				td.innerHTML = value["due_date"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["payment_date"];
				tr.appendChild(td);
	
				td = document.createElement('td');
				td.innerHTML = value["notes"];
				tr.appendChild(td);
	        
				table.appendChild(tr);
				length = length + 1;
			});
			
			// Add error div if no rows are fetched
			if (length == 0) {
				$('table').remove();
				div_content = '<span class="not_found_logo"></span>' + 
				'<p class="not_found1">No results found</p>' + 
				'<p class="not_found2">Try adjusting your search to find what you are looking for.</p>' + 
				'<text class="not_found_clear" onClick="window.location.reload();">Clear Search</text>';
				
				var grid = document.getElementById('grid_id');
				var not_found_div = document.createElement('div');
				not_found_div.innerHTML = div_content;
				not_found_div.setAttribute('class', 'not_found_div');
				grid.appendChild(not_found_div);
			}
			
			// Due dates less than current date are marked red
			$(function () {
				let today = new Date();
				$('.due').each(function() {
					let val = new Date($(this).text());
					if (val < today) {
						$(this).addClass('due_red');
					}
				});
			});
		});
	};
};