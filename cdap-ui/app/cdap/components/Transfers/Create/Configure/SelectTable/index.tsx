/*
 * Copyright © 2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import * as React from 'react';
import withStyles, { WithStyles, StyleRules } from '@material-ui/core/styles/withStyles';
import { transfersCreateConnect } from 'components/Transfers/Create/context';
import StepButtons from '../../StepButtons';
import { getCurrentNamespace } from 'services/NamespaceStore';
import { MyDeltaApi } from 'api/delta';
import If from 'components/If';
import { Checkbox, FormControlLabel, Radio } from '@material-ui/core';
import { objectQuery } from 'services/helpers';
import intersection from 'lodash/intersection';

const styles = (theme): StyleRules => {
  return {
    contentContainer: {
      display: 'grid',
      gridTemplateColumns: '1fr 2fr',
      gridColumnGap: '50px',
      height: '60vh',
      '& > div': {
        height: '100%',
        overflowY: 'auto',
      },
    },
    preview: {
      color: theme.palette.blue[100],
      cursor: 'pointer',
    },
    radioLabel: {
      display: 'inline-block',
      verticalAlign: 'middle',
    },
    tableContainer: {
      '& .table th': {
        verticalAlign: 'middle',
      },
      '& .table td': {
        verticalAlign: 'middle',
        '&:first-child': {
          width: '50px',
        },
        '&:last-child': {
          width: '75px',
        },
      },
    },
    thead: {
      backgroundColor: theme.palette.grey[600],
      '& th > span': {
        display: 'block',
      },
    },
    tableName: {
      fontWeight: 'bold',
      marginTop: '5px',
    },
    type: {
      fontWeight: 400,
      fontSize: '11px',
    },
  };
};

interface ISelectTable extends WithStyles<typeof styles> {
  source: {
    plugin: {
      properties: {
        host: string;
        port: string;
        user: string;
        password: string;
        databaseWhiteList: string;
        tableWhiteList?: string;
      };
    };
  };
  updateTable: (tables) => void;
}

const SelectTableView: React.SFC<ISelectTable> = ({ source, classes, updateTable }) => {
  const [tables, setTables] = React.useState([]);
  const [error, setError] = React.useState(null);
  const [data, setData] = React.useState(null);
  const [activeTable, setActiveTable] = React.useState(null);
  const [selected, setSelected] = React.useState([]);
  const [radio, setRadio] = React.useState('all');

  const dbInfo = objectQuery(source, 'plugin', 'properties') || {};

  const requestBody = {
    host: dbInfo.host,
    port: dbInfo.port,
    user: dbInfo.user,
    password: dbInfo.password,
  };

  React.useEffect(() => {
    const params = {
      context: getCurrentNamespace(),
      database: dbInfo.databaseWhiteList,
    };

    MyDeltaApi.getTables(params, requestBody).subscribe((res) => {
      const tableWhiteList = (objectQuery(source, 'plugin', 'properties', 'tableWhiteList') || '')
        .split(',')
        .map((fullTable) => {
          return fullTable.split('.')[1];
        })
        .filter((tableName) => tableName);
      setTables(res);
      setSelected(intersection(res, tableWhiteList));

      if (tableWhiteList.length === 0) {
        setRadio('all');
      } else {
        setRadio('individual');
      }
    }, setError);
  }, []);

  function fetchData(table) {
    const params = {
      context: getCurrentNamespace(),
      database: dbInfo.databaseWhiteList,
      table,
    };

    MyDeltaApi.sampleData(params, requestBody).subscribe(setData, setError);
  }

  function handlePreviewClick(table) {
    if (activeTable === table) {
      setActiveTable(null);
      setData(null);
    } else {
      setActiveTable(table);
      fetchData(table);
    }
  }

  function toggleTable(table) {
    const newSelected = [...selected];
    const index = newSelected.indexOf(table);

    if (index === -1) {
      newSelected.push(table);
    } else {
      newSelected.splice(index, 1);
    }

    setSelected(newSelected);
  }

  function renderData() {
    if (!data || radio === 'all') {
      return null;
    }

    return (
      <div>
        <h2>{activeTable} - preview</h2>
        <div>{data.columns.length} columns</div>
        <div>
          <table className="table table-bordered">
            <thead className={classes.thead}>
              <tr>
                {data.columns.map((header) => {
                  return (
                    <th key={header.name}>
                      <span className={classes.type}>{header.type}</span>
                      <span className={classes.tableName}>{header.name}</span>
                    </th>
                  );
                })}
              </tr>
            </thead>

            <tbody>
              {data.data.map((row, i) => {
                return (
                  <tr key={i}>
                    {data.columns.map((column, j) => {
                      return <td key={j}>{row[column.name]}</td>;
                    })}
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    );
  }

  function toggleAll() {
    if (selected.length === tables.length) {
      setSelected([]);
    } else {
      setSelected([...tables]);
    }
  }

  function onNext() {
    if (radio === 'all') {
      updateTable(null);
      return;
    }

    const tablesList = selected
      .filter((table) => table)
      .map((table) => {
        return `${dbInfo.databaseWhiteList}.${table}`;
      })
      .join(',');

    updateTable(tablesList);
  }

  function renderTableList() {
    if (radio === 'all') {
      return null;
    }

    return (
      <div className={classes.tableContainer}>
        <h2>Select tables to replicate</h2>
        <div>
          {selected.length} of {tables.length} tables selected
        </div>
        <table className="table">
          <thead>
            <tr>
              <th>
                <Checkbox
                  checked={selected.length === tables.length}
                  onChange={toggleAll}
                  color="primary"
                />
              </th>
              <th>Table</th>
              <th />
            </tr>
          </thead>

          <tbody>
            {tables.map((table) => {
              return (
                <tr key={table}>
                  <td>
                    <Checkbox
                      checked={selected.indexOf(table) !== -1}
                      onChange={toggleTable.bind(null, table)}
                      color="primary"
                    />
                  </td>
                  <td>{table}</td>
                  <td>
                    <span
                      className={classes.preview}
                      onClick={handlePreviewClick.bind(null, table)}
                    >
                      {activeTable === table ? 'Hide' : 'Preview'}
                    </span>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    );
  }

  function handleRadioChange(e) {
    setRadio(e.target.value);
  }

  return (
    <div>
      <If condition={error}>
        <div className="text-danger">{JSON.stringify(error, null, 2)}</div>
      </If>

      <div>
        <Radio
          checked={radio === 'all'}
          onChange={handleRadioChange}
          value="all"
          name="radio-button-demo"
        />
        <h4 className={classes.radioLabel}>
          Replicate all tables in the database (this will replicate new tables created in the
          database)
        </h4>
      </div>

      <div>
        <Radio
          checked={radio === 'individual'}
          onChange={handleRadioChange}
          value="individual"
          name="radio-button-demo"
        />
        <h4 className={classes.radioLabel}>Choose tables to replicate</h4>
      </div>
      <br />
      <div className={classes.contentContainer}>
        {renderTableList()}
        {renderData()}
      </div>

      <StepButtons onNext={onNext} />
    </div>
  );
};

const StyledSelectTable = withStyles(styles)(SelectTableView);
const SelectTable = transfersCreateConnect(StyledSelectTable);
export default SelectTable;