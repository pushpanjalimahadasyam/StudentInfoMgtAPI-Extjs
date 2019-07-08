Ext.onReady(function () {
  Ext.define('StudentApp.model.Student', {
    extend: 'Ext.data.Model',
    fields: [
    	{name: 'name', type: 'string'},
        {name: 'birthDate',  type: 'date'},
        {name: 'address', type: 'string'},
        {name: 'regNo',  type: 'int'},
        {name: 'id', type: 'int'}
    ]
  });
 
  Ext.define('StudentApp.store.Students', {
    extend  : 'Ext.data.Store',
    storeId	: 'studentStore',
    pageSize: 5,
    model   : 'StudentApp.model.Student',
    fields  : ['name', 'birthDate', 'address','regNo', 'id'],
    params: {
        start: 0,
        limit: 5
    },
    proxy	: {
        type	: 'ajax',
        url		: '/students/paging',
        reader	: {
            type	: 'json',
            root: 'students',
            totalProperty: 'total'
        }
    },
    autoLoad: true
  });
 
  Ext.define('StudentApp.view.StudentsList', {
    extend	: 'Ext.grid.Panel',
    alias	: 'widget.studentslist',
    title	: 'Students List',
    itemId: 'studentDetailsGridItemId',
    width:'95%',
    cls: 'extra-alt',
    store	: 'Students',
    initComponent: function () {
      this.tbar = [{
        text    : 'Add Student',
        action  : 'add',
        iconCls : 'student-add'
      }];
      this.columns = [
    	  { text: 'Student Name',   dataIndex: 'name', flex: 0.5 },
          { text: 'DOB',     dataIndex: 'birthDate',   xtype: 'datecolumn',   format:'Y-m-d',flex: 0.2 },
          { text: 'Address',   dataIndex: 'address',flex: 0.5},
          { text: 'Registration Number',   dataIndex: 'regNo', xtype: 'numbercolumn', format:'0000000000',flex: 0.2, tdCls:'rollnumber-column'},
          { text: 'Id',  dataIndex: 'id', hidden:true, xtype: 'numbercolumn', format:'0000000000' } 
        
      ];
      this.bbar = [{
              xtype: 'pagingtoolbar',
              store: 'Students',
              pageSize: 5,
              displayInfo: true,
              displayMsg: 'Displaying Students {0} to {1} of {2} &nbsp;records ',
              emptyMsg: "No records to display&nbsp;"
          }];
      this.callParent(arguments);
    }
  });
 
    Ext.define('StudentApp.view.StudentsForm', {
      extend  : 'Ext.window.Window',
      alias   : 'widget.studentForm',
      title   : 'Add Student',
      width   : 350,
      layout  : 'fit',
      resizable: false,
      closeAction: 'hide',
      modal   : true,
      config  : {
        recordIndex : 0,
        action : ''
      },
      items   : [{
        xtype : 'form',
        layout: 'anchor',
        bodyStyle: {
          background: 'none',
          padding: '10px',
          border: '0'
        },
        defaults: {
          xtype : 'textfield',
          anchor: '100%'
        },
        items : [
        	{
        	    name  : 'name',
        	    fieldLabel: 'Student Name',
        	    allowBlank: false,
        	    maxLength:130,
        	    minLength:2
        	  },{
        	    name: 'regNo',
        	    fieldLabel: 'Registration Number',
        	    xtype : 'numberfield',
        	    maxLength:10,
        	    minLength:2,
        	    allowBlank: false,
        	    hideTrigger:true,
        	    keyNavEnabled:false,
        	    mouseWheelEnabled:false
        	  },{
        	    name: 'birthDate',
        	    xtype : 'datefield',
        	    fieldLabel: 'Date Of Birth',
        	    format: 'Y-d-m',
        	    allowBlank: false,
        	    maxValue: new Date()
        	  },{
        	    name: 'address',
        	    xtype: 'textareafield',
        	    fieldLabel: 'Address',
        	    allowBlank: false,
        	    maxLength:255,
        	    minLength:5
        	  },
        	  {
        	    xtype : 'hidden',
        	    name: 'id'
        	    }
        ]
      }],
      buttons: [{
        text: 'OK',
        action: 'add'
      },{
        text    : 'Reset',
        handler : function () { 
          this.up('window').down('form').getForm().reset(); 
        }
      },{
        text   : 'Cancel',
        handler: function () { 
          this.up('window').close();
        }
      }]
    });
 
  Ext.define('StudentApp.controller.Students', {
    extend  : 'Ext.app.Controller',
    stores  : ['Students'],
    views   : ['StudentsList', 'StudentsForm'],
    refs    : [{
      ref   : 'formWindow',
      xtype : 'studentForm',
      selector: 'studentForm',
      autoCreate: true
    }],
    init: function () {
      this.control({
        'studentslist > toolbar > button[action=add]': {
          click: this.showAddForm
        },
        'studentslist': {
          itemdblclick: this.onRowdblclick
        },
        'studentForm button[action=add]': {
          click: this.doAddStudent
        }
      });
    },
    onRowdblclick: function(me, record, item, index) {
      var win = this.getFormWindow();
      win.setTitle('Edit Student');
      win.setAction('edit');
      win.setRecordIndex(index);
      win.down('form').getForm().setValues(record.getData());
      win.show();
    },
    showAddForm: function () {
      var win = this.getFormWindow();
      win.setTitle('Add Student');
      win.setAction('add');
      win.down('form').getForm().reset();
      win.show();
    },
    doAddStudent: function () {
      var win = this.getFormWindow();
      var store = this.getStudentsStore();
      var form = win.down('form').getForm();
      if (form.isValid()) {
	      var values = win.down('form').getValues();
	      
	      var action = win.getAction();
	      var url = '';
	      var method = '';
	      if(action == 'edit') {
	    	  url = '/students';
	    	  method = 'PUT'
	      }
	      else {
	    	  url = '/students';
	    	  method = 'POST'
	      }
	      Ext.Ajax.request({
	  		url		: url,
	    	method  : method,
	    	jsonData: values,	
	    	success: function(response){
	    		var grid = Ext.ComponentQuery.query('grid[itemId=studentDetailsGridItemId]');
	    		//Ext.fly(grid[0].getView().getNode(0)).addCls("myHighlight");
	    	    	store.load({
	    	    		callback: function(records, operation, success, response) {
	                      if(success){
	                    	  grid[0].getSelectionModel().select(0);
	                         }
	    	    		}
	    	    	});
	    	    	
	    		},
	    		failure: function(response){
	    			var obj = Ext.decode(response.responseText);
	    			alert("Error: "+response.status+"---"+ obj.message);
		   	}
	    	});
	      win.close();
	    }
    }
  });
 
  Ext.application({
    name  : 'StudentApp',
    controllers: ['Students'],
      launch: function () {
        Ext.widget('studentslist', {
          //width : 1000,
          height: 300,
          renderTo: 'output'
        });
      }
    }
  );
});